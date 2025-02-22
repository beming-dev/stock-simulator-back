package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.service.JwtService;
import com.stock.stock_simulator.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getenv;

@RestController
@RequestMapping("/api/oauth")
public class OAuthController {
    private final JwtService jwtService;
    private final UserService userService;

    public OAuthController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @GetMapping("/google")
    public void handleGoogleOAuth(@RequestParam String code, HttpServletResponse response) throws IOException {
        Map<String, String> env = getenv();

        // Exchange the Authorization Code for an Access Token
        String tokenUrl = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();
        String FRONT_BASE_URL=env.get("FRONT_BASE_URL");
        String BACK_BASE_URL=env.get("BACK_BASE_URL");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", env.get("GOOGLE_ID"));
        params.add("client_secret", env.get("GOOGLE_SECRET"));
        params.add("redirect_uri", BACK_BASE_URL + "/api/oauth/google");
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenUrl, request, Map.class);

        // Parse the Access Token
        String accessToken = (String) tokenResponse.getBody().get("access_token");

        // Fetch user info with Access Token
        String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo";
        HttpHeaders authHeader = new HttpHeaders();
        authHeader.setBearerAuth(accessToken);

        HttpEntity<?> userInfoRequest = new HttpEntity<>(authHeader);
        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequest, Map.class);

        Map<String, Object> userInfo = userInfoResponse.getBody();
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String id = (String) userInfo.get("id");

        userService.upsertUser(id, name, "google");

        // Generate JWT token
        String jwtToken = jwtService.generateToken(id, email, name);

        // Redirect to frontend with token
//        String redirectUrl = "http://localhost:5173/login-success?token=" + jwtToken;
        String redirectUrl = FRONT_BASE_URL + "/login-success?token=" + jwtToken;
        response.sendRedirect(redirectUrl);
    }
}