package org.alexgiou.ecommerce.service.impl;

import lombok.RequiredArgsConstructor;
import org.alexgiou.ecommerce.domain.ROLE;
import org.alexgiou.ecommerce.entity.cart.Cart;
import org.alexgiou.ecommerce.entity.user.User;
import org.alexgiou.ecommerce.repository.CartRepository;
import org.alexgiou.ecommerce.repository.UserRepository;
import org.alexgiou.ecommerce.request.RegisterRequest;
import org.alexgiou.ecommerce.service.IAuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/30/2024
 */
@Service
@RequiredArgsConstructor
public class IAuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    @Override
    public void register(RegisterRequest registerRequest) {
        // Validate email uniqueness
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFullName(registerRequest.getFullName());
        user.setRole(ROLE.CUSTOMER);
        user.setMobileNumber(registerRequest.getMobileNumber());
        user.setPassword(passwordEncoder.encode(registerRequest.getOtp()));

        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

    }
}
