package com.francisco.requestmanager

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MercadoLibreApi {
    @GET(APIConstants.BASE_SEARCH_PATCH)
    suspend fun getDefaultProducts(
        @Path("site_id") siteId: String = APIConstants.BASE_SITE_ID,
        @Query("category") categoryId: String = APIConstants.BASE_CATEGORY_ID
    ): ProductListResponse

    @GET(APIConstants.BASE_SEARCH_PATCH)
    suspend fun getProductsByQuery(
        @Path("site_id") siteId: String = APIConstants.BASE_SITE_ID,
        @Query("q") query: String
    ): ProductListResponse

    @GET(APIConstants.BASE_DETAILS_PATCH)
    suspend fun getDetailsProduct(@Path("id_product") productId: String): ProductDetailResponse
}