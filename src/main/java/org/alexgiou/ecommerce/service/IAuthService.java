package org.alexgiou.ecommerce.service;

import org.alexgiou.ecommerce.request.RegisterRequest;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/23/2024
 */

public interface IAuthService {

    void register(RegisterRequest registerRequest);
}
