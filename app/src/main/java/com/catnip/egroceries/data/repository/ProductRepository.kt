package com.catnip.egroceries.data.repository

import com.catnip.egroceries.data.datasource.dummy.CategoryDataSource
import com.catnip.egroceries.data.datasource.dummy.ProductDataSource
import com.catnip.egroceries.model.Category
import com.catnip.egroceries.model.Product

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProductRepository {
    fun getCategories(): List<Category>
    fun getProducts(): List<Product>
}

class ProductRepositoryImpl(
    private val categoryDataSource: CategoryDataSource,
    private val productDataSource: ProductDataSource
) : ProductRepository {

    override fun getCategories(): List<Category> {
        return categoryDataSource.getProductCategory()
    }

    override fun getProducts(): List<Product> {
        return productDataSource.getProductList()
    }
}