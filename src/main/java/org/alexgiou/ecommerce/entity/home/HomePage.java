package org.alexgiou.ecommerce.entity.home;

import lombok.Data;
import org.alexgiou.ecommerce.entity.category.Deal;

import java.util.List;

/**
 * @author: Alexandros Giounan
 * @code @created: 10/21/2024
 */
@Data
public class HomePage {

    private List<HomeCategory> grid;
    private List<HomeCategory> electricCategories;
    private List<HomeCategory> shopByCategories;
    private List<HomeCategory> dealsCategories;
    private List<Deal> deals;
}
