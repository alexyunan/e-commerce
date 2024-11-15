package org.alexgiou.ecommerce.response;

import org.springframework.http.HttpStatus;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/12/2024
 */

public record ApiResponse(String message, HttpStatus status){
}
