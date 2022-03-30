package com.example.baseapplication.data.retrofit

import com.example.baseapplication.BuildConfig
import com.example.baseapplication.data.model.LottoModel
import com.example.baseapplication.data.retrofit.RetrofitHelper.enqueueWithRetry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofit(private val listener: RetrofitListener) {
    private var mRetrofit: Retrofit? = null
    private var mRetrofitService: RetrofitApiService? = null

    companion object {
        private const val baseUrl = "https://www.nlotto.co.kr"
        private const val dev_baseUrl = "https://www.nlotto.co.kr"
        private const val RETRY_COUNT = 3
    }

    init {
        if (BuildConfig.DEBUG) {
            mRetrofit = Retrofit.Builder()
                .baseUrl(dev_baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            mRetrofitService = mRetrofit?.create(RetrofitApiService::class.java)
        } else {
            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            mRetrofitService = mRetrofit?.create(RetrofitApiService::class.java)
        }
    }

    interface RetrofitListener {
        fun onComplete(data: Any?, url: String?)
        fun onFail(errCode: Int, url: String?)
        fun onFail(message: String?, url: String?)
    }

    //로또 조회
        fun getLotto(drwNo: Int) {
        val call: Call<LottoModel> = mRetrofitService!!.getLottoInfo(drwNo)
//        Utils.debug("getMsgCntList", "URL : " + call.request().toString())
        enqueueWithRetry<LottoModel>(call, RETRY_COUNT, object : Callback<LottoModel> {
            override fun onResponse(call: Call<LottoModel>, response: Response<LottoModel>
            ) {
                if (response.isSuccessful) {
                    listener.onComplete(response.body(), call.request().url().toString())
                } else {
                    listener.onFail(response.code(), call.request().url().toString())
                }
            }

            override fun onFailure(call: Call<LottoModel>, t: Throwable) {
                listener.onFail(t.toString(), call.request().url().toString())
            }
        })
    }

//    //지점
//    val branchList: Unit
//        get() {
//            val call: Call<ArrayList<ItemEx.Branch>> = mRetrofitService.getBranchList()
//            Utils.debug("getBranchList", "URL : " + call.request().toString())
//            enqueueWithRetry<ArrayList<ItemEx.Branch>>(
//                call,
//                RETRY_COUNT,
//                object : Callback<ArrayList<ItemEx.Branch?>?> {
//                    override fun onResponse(
//                        call: Call<ArrayList<ItemEx.Branch?>?>,
//                        response: Response<ArrayList<ItemEx.Branch?>?>
//                    ) {
//                        if (response.isSuccessful) {
//                            listener.onComplete(response.body(), call.request().url().toString())
//                        } else {
//                            listener.onFail(response.code(), call.request().url().toString())
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ArrayList<ItemEx.Branch?>?>, t: Throwable) {
//                        listener.onFail(t.toString(), call.request().url().toString())
//                    }
//                })
//        }
//
//    //홈 메시지 갯수
//    //[20.01.16] 로그인 안한 고객에게 push 보내기
//    //public void getMsgCntList(String clientcode, String personcode, String push_id) {
//    fun getMsgCntList(clientcode: String?, personcode: String?) {
//        val call: Call<ArrayList<ItemEx.MsgCnt>> =
//            mRetrofitService.getMsgCntList(clientcode, personcode)
//        Utils.debug("getMsgCntList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MsgCnt>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MsgCnt?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MsgCnt?>?>,
//                    response: Response<ArrayList<ItemEx.MsgCnt?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MsgCnt?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //홍보문자 리스트
//    //[20.01.16] 로그인 안한 고객에게 push 보내기
//    //public void getPushMsgList(String personcode, String clientcode, String push_id) {
//    fun getPushMsgList(personcode: String?) {
//        val call: Call<ArrayList<ItemEx.PushMsg>> = mRetrofitService.getPushMsgList(personcode)
//        Utils.debug("getPushMsgList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.PushMsg>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.PushMsg?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.PushMsg?>?>,
//                    response: Response<ArrayList<ItemEx.PushMsg?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.PushMsg?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //홍보문자 상세보기
//    //[20.01.16] 로그인 안한 고객에게 push 보내기
//    //public void getPushDetailList(String clientcode, String personcode, String msgcode, String push_id) {
//    fun getPushDetailList(clientcode: String?, personcode: String?, msgcode: String?) {
//        val call: Call<ArrayList<ItemEx.PushDetail>> =
//            mRetrofitService.getPushDetailList(clientcode, personcode, msgcode)
//        Utils.debug("getPushDetailList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.PushDetail>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.PushDetail?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.PushDetail?>?>,
//                    response: Response<ArrayList<ItemEx.PushDetail?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.PushDetail?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //홍보문자 확인여부 회신(포인트지급)
//    fun getPushReceive(
//        clientcode: String?,
//        savezonecode: String?,
//        personcode: String?,
//        msgcode: String?,
//        recv_point: String?,
//        msgtitle: String?,
//        url: String?
//    ) {
//        val call: Call<ArrayList<ItemEx.PushReceive>> = mRetrofitService.getPushReceive(
//            clientcode,
//            savezonecode,
//            personcode,
//            msgcode,
//            recv_point,
//            msgtitle,
//            url
//        )
//        Utils.debug("getPushReceive", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.PushReceive>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.PushReceive?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.PushReceive?>?>,
//                    response: Response<ArrayList<ItemEx.PushReceive?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.PushReceive?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //바코드 포인트 조회
//    //    public void getPointSearch(String personcode) {
//    //        Call<ArrayList<ItemEx.PointSearch>> call = mRetrofitService.getPointSearch(personcode);
//    //        RetrofitHelper.enqueueWithRetry(call, RETRY_COUNT, new Callback<ArrayList<ItemEx.PointSearch>>() {
//    //            @Override
//    //            public void onResponse(Call<ArrayList<ItemEx.PointSearch>> call, Response<ArrayList<ItemEx.PointSearch>> response) {
//    //                if(response.isSuccessful()) {
//    //                    listener.onComplete(response.body());
//    //                } else {
//    //                    listener.onFail(response.code(), call.request().url().toString());
//    //                }
//    //            }
//    //
//    //            @Override
//    //            public void onFailure(Call<ArrayList<ItemEx.PointSearch>> call, Throwable t) {
//    //                listener.onFail(t.toString(), call.request().url().toString());
//    //            }
//    //        });
//    //    }
//    //메인배너
//    fun getBannerList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.MainBanner>> = mRetrofitService.getBannerList(clientcode)
//        Utils.debug("getBannerList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MainBanner>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MainBanner?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MainBanner?>?>,
//                    response: Response<ArrayList<ItemEx.MainBanner?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MainBanner?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 - HOT
//    fun getShoppingHotList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingHotList>> =
//            mRetrofitService.getShoppingHotList(clientcode)
//        Utils.debug("getShoppingHotList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingHotList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingHotList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingHotList?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingHotList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingHotList?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 식품관 리스트
//    fun getShoppingFoodList(clientcode: String?, foodkey: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingFoodList>> =
//            mRetrofitService.getShoppingFoodList(clientcode, foodkey)
//        Utils.debug("getShoppingFoodList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingFoodList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingFoodList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingFoodList?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingFoodList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingFoodList?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 식품관 상세내역
//    fun getShoppingFoodDetail(clientcode: String?, foodkey: String?, menu_code: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingFoodDetail>> =
//            mRetrofitService.getShoppingFoodDetail(clientcode, foodkey, menu_code)
//        Utils.debug("getShoppingFoodDetail", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingFoodDetail>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingFoodDetail?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingFoodDetail?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingFoodDetail?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingFoodDetail?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 패션관 리스트
//    fun getShoppingFashionList(clientcode: String?, tab_gn: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingFashionList>> =
//            mRetrofitService.getShoppingFashionList(clientcode, tab_gn)
//        Utils.debug("getShoppingFashionList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingFashionList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingFashionList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingFashionList?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingFashionList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingFashionList?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 패션관 상세내역
//    fun getShoppingFashionDetail(clientcode: String?, tab_gn: String?, menu_gn: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingFashionDetail>> =
//            mRetrofitService.getShoppingFashionDetail(clientcode, tab_gn, menu_gn)
//        Utils.debug("getShoppingFashionDetail", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingFashionDetail>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingFashionDetail?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingFashionDetail?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingFashionDetail?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingFashionDetail?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 브랜드관 리스트
//    fun getShoppingBrandList(clientcode: String?, tab_gn: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingFashionList>> =
//            mRetrofitService.getShoppingBrandList(clientcode, tab_gn)
//        Utils.debug("getShoppingBrandList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingFashionList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingFashionList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingFashionList?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingFashionList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingFashionList?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 브랜드관 업체리스트
//    fun getBrandList(clientcode: String?, tab_gn: String?, menu_gn: String?) {
//        val call: Call<ArrayList<ItemEx.BrandList>> =
//            mRetrofitService.getBrandList(clientcode, tab_gn, menu_gn)
//        Utils.debug("getBrandList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.BrandList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.BrandList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.BrandList?>?>,
//                    response: Response<ArrayList<ItemEx.BrandList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.BrandList?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쇼핑뉴스 패션관 상세내역
//    fun getShoppingBrandDetail(clientcode: String?, tab_gn: String?, newskey: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingBrandDetail>> =
//            mRetrofitService.getShoppingBrandDetail(clientcode, tab_gn, newskey)
//        Utils.debug("getShoppingBrandDetail", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingBrandDetail>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingBrandDetail?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingBrandDetail?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingBrandDetail?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingBrandDetail?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //브랜드검색 리스트
//    fun getShoppingBrandFind(clientcode: String?, brandname: String?) {
//        val call: Call<ArrayList<ItemEx.ShoppingBrandFind>> =
//            mRetrofitService.getShoppingBrandFind(clientcode, brandname)
//        Utils.debug("getShoppingBrandFind", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.ShoppingBrandFind>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.ShoppingBrandFind?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.ShoppingBrandFind?>?>,
//                    response: Response<ArrayList<ItemEx.ShoppingBrandFind?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.ShoppingBrandFind?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //층별정보
//    fun getLayerList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.LayerInfo>> = mRetrofitService.getLayerList(clientcode)
//        Utils.debug("getLayerList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.LayerInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.LayerInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.LayerInfo?>?>,
//                    response: Response<ArrayList<ItemEx.LayerInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.LayerInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //층별 점포 상세정보
//    fun getLayerDetailList(clientcode: String?, layer: String?) {
//        val call: Call<ArrayList<ItemEx.LayerDetailInfo>> =
//            mRetrofitService.getLayerDetailList(clientcode, layer)
//        Utils.debug("getLayerDetailList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.LayerDetailInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.LayerDetailInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.LayerDetailInfo?>?>,
//                    response: Response<ArrayList<ItemEx.LayerDetailInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.LayerDetailInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //[19.12.13] 층별검색
//    fun getLayerSearch(clientcode: String?, layer: String?, clientname: String?) {
//        val call: Call<ArrayList<ItemEx.LayerSearchInfo>> =
//            mRetrofitService.getLayerSearch(clientcode, layer, clientname)
//        Utils.debug("getLayerSearch", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.LayerSearchInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.LayerSearchInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.LayerSearchInfo?>?>,
//                    response: Response<ArrayList<ItemEx.LayerSearchInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.LayerSearchInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //전단지
//    fun getPaperList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.PaperInfo>> = mRetrofitService.getPaperList(clientcode)
//        Utils.debug("getPaperList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.PaperInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.PaperInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.PaperInfo?>?>,
//                    response: Response<ArrayList<ItemEx.PaperInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.PaperInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //고객가입
//    fun reqJoin(
//        personcode: String?,
//        personname: String?,
//        persontel: String?,
//        personbirth: String?,
//        marry: String?,
//        personsex: String?,
//        clientcode: String?,
//        savezonecode: String?,
//        phonegubun: String?,
//        edit_site: String?,
//        dm_yn: String?,
//        sms_yn: String?,
//        pushyn: String?,
//        cpost: String?,
//        personjuso: String?,
//        personbunji: String?,
//        j_juso: String?,
//        postkey: String?,
//        member_type: String?,
//        push_id: String?,
//        joingubun: String?,
//        url: String?
//    ) {
//        val call: Call<ArrayList<ItemEx.MemberReturnInfo>> = mRetrofitService.reqJoin(
//            personcode, personname, persontel, personbirth, marry, personsex,
//            clientcode, savezonecode, phonegubun, edit_site, dm_yn, sms_yn, pushyn,
//            cpost, personjuso, personbunji, j_juso, postkey,
//            member_type, push_id, joingubun, url
//        )
//        Utils.debug("reqJoin", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MemberReturnInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MemberReturnInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MemberReturnInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MemberReturnInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.MemberReturnInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //회원탈퇴
//    fun reqMemberLeave(personcode: String?, leave_yn: String?, url: String?) {
//        val call: Call<ArrayList<ItemEx.MemberLeaveInfo>> =
//            mRetrofitService.reqMemberLeave(personcode, leave_yn, url)
//        Utils.debug("reqMemberLeave", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MemberLeaveInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MemberLeaveInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MemberLeaveInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MemberLeaveInfo?>?>
//                ) {
//                    if (response.isSuccessful) listener.onComplete(
//                        response.body(),
//                        call.request().url().toString()
//                    ) else listener.onFail(response.code(), call.request().url().toString())
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.MemberLeaveInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //기존회원체크
//    fun reqMemberExist(kor_name: String?, mtel: String?, birthday: String?, sexgbn: String?) {
//        val call: Call<ArrayList<ItemEx.MemberExistInfo>> =
//            mRetrofitService.reqMemberExist(kor_name, mtel, birthday, sexgbn)
//        Utils.debug("reqMemberExist", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MemberExistInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MemberExistInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MemberExistInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MemberExistInfo?>?>
//                ) {
//                    if (response.isSuccessful) listener.onComplete(
//                        response.body(),
//                        call.request().url().toString()
//                    ) else listener.onFail(response.code(), call.request().url().toString())
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.MemberExistInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //기존회원 리스트
//    fun reqMemberExistList(kor_name: String?, mtel: String?, birthday: String?, sexgbn: String?) {
//        val call: Call<ArrayList<ItemEx.MemberExistInfoList>> =
//            mRetrofitService.reqMemberExistList(kor_name, mtel, birthday, sexgbn)
//        Utils.debug("reqMemberExistList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MemberExistInfoList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MemberExistInfoList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MemberExistInfoList?>?>,
//                    response: Response<ArrayList<ItemEx.MemberExistInfoList?>?>
//                ) {
//                    if (response.isSuccessful) listener.onComplete(
//                        response.body(),
//                        call.request().url().toString()
//                    ) else listener.onFail(response.code(), call.request().url().toString())
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.MemberExistInfoList?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //고객정보 검색
//    fun reqMemberInfo(personcode: String?) {
//        val call: Call<ArrayList<ItemEx.MemberInfo>> = mRetrofitService.reqMemberInfo(personcode)
//        Utils.debug("reqMemberInfo", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MemberInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MemberInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MemberInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MemberInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MemberInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //고객정보수정
//    //    public void reqUpdate(String clientcode, String personcode, String oldtel, String newtel, String personbirth, String personsex,
//    //                          String cpost, String personjuso, String personbunji, String phonegubun, String push_id, String personname,
//    //                          String pushyn, String dm_yn) {
//    //        Call<ArrayList<ItemEx.MemberReturnUpInfo>> call = mRetrofitService.reqUpdate(clientcode, personcode, oldtel, newtel, personbirth, personsex,
//    //                cpost, personjuso, personbunji, phonegubun, push_id, personname, pushyn, dm_yn);
//    //        RetrofitHelper.enqueueWithRetry(call, RETRY_COUNT, new Callback<ArrayList<ItemEx.MemberReturnUpInfo>>() {
//    //            @Override
//    //            public void onResponse(Call<ArrayList<ItemEx.MemberReturnUpInfo>> call, Response<ArrayList<ItemEx.MemberReturnUpInfo>> response) {
//    //                if(response.isSuccessful()) {
//    //                    listener.onComplete(response.body(), call.request().url().toString());
//    //                } else {
//    //                    listener.onFail(response.code(), call.request().url().toString());
//    //                }
//    //            }
//    //
//    //            @Override
//    //            public void onFailure(Call<ArrayList<ItemEx.MemberReturnUpInfo>> call, Throwable t) {
//    //                listener.onFail(t.toString(), call.request().url().toString());
//    //            }
//    //        });
//    //    }
//    //로그인
//    fun reqLogin(personname: String?, persontel: String?) {
//        val call: Call<ArrayList<ItemEx.LoginInfo>> =
//            mRetrofitService.reqLogin(personname, persontel)
//        Utils.debug("reqLogin", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.LoginInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.LoginInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.LoginInfo?>?>,
//                    response: Response<ArrayList<ItemEx.LoginInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.LoginInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //토큰 갱신
//    fun changeToken(personcode: String?, push_id: String?) {
//        val call: Call<ArrayList<ItemEx.TokenResp>> =
//            mRetrofitService.changeToken(personcode, push_id, "android")
//        Utils.debug("changeToken", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.TokenResp>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.TokenResp?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.TokenResp?>?>,
//                    response: Response<ArrayList<ItemEx.TokenResp?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.TokenResp?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //쿠폰 리스트
//    fun getCouponList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.CouponInfo>> = mRetrofitService.getCouponList(clientcode)
//        Utils.debug("getCouponList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.CouponInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.CouponInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.CouponInfo?>?>,
//                    response: Response<ArrayList<ItemEx.CouponInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.CouponInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //이벤트 리스트
//    fun getEventList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.EventList>> = mRetrofitService.getEventList(clientcode)
//        Utils.debug("getEventList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.EventList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.EventList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.EventList?>?>,
//                    response: Response<ArrayList<ItemEx.EventList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.EventList?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //더보기
//    fun getMoreInfo(clientcode: String?, personcode: String?) {
//        val call: Call<ArrayList<ItemEx.MoreInfo>> =
//            mRetrofitService.getMoreInfo(clientcode, personcode)
//        Utils.debug("getMoreInfo", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MoreInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MoreInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MoreInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MoreInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MoreInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //MY 포인트
//    fun getMyPoint(personcode: String?) {
//        val call: Call<ArrayList<ItemEx.MyPoint>> = mRetrofitService.getMyPoint(personcode)
//        Utils.debug("getMyPoint", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MyPoint>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MyPoint?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MyPoint?>?>,
//                    response: Response<ArrayList<ItemEx.MyPoint?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MyPoint?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //MY 포인트 리스트
//    fun getMyPointList(personcode: String?, find_month: String?, display_gbn: String?) {
//        val call: Call<ArrayList<ItemEx.MyPointList>> =
//            mRetrofitService.getMyPointList(personcode, find_month, display_gbn)
//        Utils.debug("getMyPointList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MyPointList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MyPointList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MyPointList?>?>,
//                    response: Response<ArrayList<ItemEx.MyPointList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MyPointList?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //공지사항 리스트
//    fun getNoticeList(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.NoticeList>> = mRetrofitService.getNoticeList(clientcode)
//        Utils.debug("getNoticeList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.NoticeList>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.NoticeList?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.NoticeList?>?>,
//                    response: Response<ArrayList<ItemEx.NoticeList?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.NoticeList?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //공지사항 상세내역
//    fun getNoticeDetail(notice_year: String?, notice_num: String?) {
//        val call: Call<ArrayList<ItemEx.NoticeDetail>> =
//            mRetrofitService.getNoticeDetail(notice_year, notice_num)
//        Utils.debug("getNoticeDetail", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.NoticeDetail>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.NoticeDetail?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.NoticeDetail?>?>,
//                    response: Response<ArrayList<ItemEx.NoticeDetail?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.NoticeDetail?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //push on/off change
//    fun setPushChange(personcode: String?, pushyn: String?) {
//        val call: Call<ArrayList<ItemEx.PushOnoffResult>> =
//            mRetrofitService.setPushChange(personcode, pushyn)
//        Utils.debug("setPushChange", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.PushOnoffResult>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.PushOnoffResult?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.PushOnoffResult?>?>,
//                    response: Response<ArrayList<ItemEx.PushOnoffResult?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.PushOnoffResult?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //회원탈퇴 체크
//    fun checkLeave(personcode: String?) {
//        val call: Call<ArrayList<ItemEx.LeaveCheckResult>> = mRetrofitService.checkLeave(personcode)
//        Utils.debug("checkLeave", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.LeaveCheckResult>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.LeaveCheckResult?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.LeaveCheckResult?>?>,
//                    response: Response<ArrayList<ItemEx.LeaveCheckResult?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.LeaveCheckResult?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //app version
//    val appLatestVersion: Unit
//        get() {
//            val call: Call<ArrayList<ItemEx.AppVersion>> = mRetrofitService.getAppVersion()
//            Utils.debug("getAppLatestVersion", "URL : " + call.request().toString())
//            enqueueWithRetry<ArrayList<ItemEx.AppVersion>>(
//                call,
//                RETRY_COUNT,
//                object : Callback<ArrayList<ItemEx.AppVersion?>?> {
//                    override fun onResponse(
//                        call: Call<ArrayList<ItemEx.AppVersion?>?>,
//                        response: Response<ArrayList<ItemEx.AppVersion?>?>
//                    ) {
//                        if (response.isSuccessful) {
//                            listener.onComplete(response.body(), call.request().url().toString())
//                        } else {
//                            listener.onFail(response.code(), call.request().url().toString())
//                        }
//                    }
//
//                    override fun onFailure(
//                        call: Call<ArrayList<ItemEx.AppVersion?>?>,
//                        t: Throwable
//                    ) {
//                        listener.onFail(t.toString(), call.request().url().toString())
//                    }
//                })
//        }
//
//    //popup
//    fun getPopupInfo(clientcode: String?) {
//        val call: Call<ArrayList<ItemEx.PopupResult>> = mRetrofitService.getPopupInfo(clientcode)
//        Utils.debug("getPopupInfo", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.PopupResult>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.PopupResult?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.PopupResult?>?>,
//                    response: Response<ArrayList<ItemEx.PopupResult?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.PopupResult?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //[19.12.02] 추천인 기간 여부
//    val nominateInfo: Unit
//        get() {
//            val call: Call<ArrayList<ItemEx.NominateInfo>> = mRetrofitService.getNominateInfo()
//            Utils.debug("getNominateInfo", "URL : " + call.request().toString())
//            enqueueWithRetry<ArrayList<ItemEx.NominateInfo>>(
//                call,
//                RETRY_COUNT,
//                object : Callback<ArrayList<ItemEx.NominateInfo?>?> {
//                    override fun onResponse(
//                        call: Call<ArrayList<ItemEx.NominateInfo?>?>,
//                        response: Response<ArrayList<ItemEx.NominateInfo?>?>
//                    ) {
//                        if (response.isSuccessful) {
//                            listener.onComplete(response.body(), call.request().url().toString())
//                        } else {
//                            listener.onFail(response.code(), call.request().url().toString())
//                        }
//                    }
//
//                    override fun onFailure(
//                        call: Call<ArrayList<ItemEx.NominateInfo?>?>,
//                        t: Throwable
//                    ) {
//                        listener.onFail(t.toString(), call.request().url().toString())
//                    }
//                })
//        }
//
//    //[19.12.06] 추천인 포함 회원가입
//    fun reqJoinNominate(
//        personcode: String?,
//        personname: String?,
//        persontel: String?,
//        personbirth: String?,
//        marry: String?,
//        personsex: String?,
//        clientcode: String?,
//        savezonecode: String?,
//        phonegubun: String?,
//        edit_site: String?,
//        dm_yn: String?,
//        sms_yn: String?,
//        pushyn: String?,
//        cpost: String?,
//        personjuso: String?,
//        personbunji: String?,
//        j_juso: String?,
//        postkey: String?,
//        member_type: String?,
//        push_id: String?,
//        joingubun: String?,
//        url: String?,
//        nominate_num: String?,
//        nominate_tel: String?
//    ) {
//        val call: Call<ArrayList<ItemEx.MemberReturnInfo>> = mRetrofitService.reqJoinNominate(
//            personcode, personname, persontel, personbirth, marry, personsex,
//            clientcode, savezonecode, phonegubun, edit_site, dm_yn, sms_yn, pushyn,
//            cpost, personjuso, personbunji, j_juso, postkey,
//            member_type, push_id, joingubun, url,
//            nominate_num, nominate_tel
//        )
//        Utils.debug("reqJoinNominate", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MemberReturnInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MemberReturnInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MemberReturnInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MemberReturnInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.MemberReturnInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //[20.01.13] 앱설치고객 고객정보관리(앱설치 후 지점선택 및 선호지점 수정시)
//    fun appInstallManage(
//        newtel: String?,
//        personcode: String?,
//        clientcode: String?,
//        pushyn: String?,
//        push_id: String?
//    ) {
//        val call: Call<ArrayList<ItemEx.AppInstallInfo>> = mRetrofitService.appInstallManage(
//            newtel, personcode, clientcode, "android",
//            pushyn, push_id
//        )
//        Utils.debug("appInstallManage", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.AppInstallInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.AppInstallInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.AppInstallInfo?>?>,
//                    response: Response<ArrayList<ItemEx.AppInstallInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.AppInstallInfo?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //MY쿠폰 상품 쿠폰 리스트
//    fun getMyCouponList(tel: String?) {
//        val call: Call<ArrayList<ItemEx.MyCouponInfo>> = mRetrofitService.getMyCouponList(tel)
//        Utils.debug("getMyCouponList", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MyCouponInfo>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MyCouponInfo?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MyCouponInfo?>?>,
//                    response: Response<ArrayList<ItemEx.MyCouponInfo?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<ArrayList<ItemEx.MyCouponInfo?>?>, t: Throwable) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
//
//    //MY쿠폰 상품 쿠폰 지급
//    fun reqMyCouponRev(win_key: String?, personcode: String?) {
//        val call: Call<ArrayList<ItemEx.MyCouponRevResult>> =
//            mRetrofitService.reqMyCouponRev(win_key, personcode)
//        Utils.debug("reqMyCouponRev", "URL : " + call.request().toString())
//        enqueueWithRetry<ArrayList<ItemEx.MyCouponRevResult>>(
//            call,
//            RETRY_COUNT,
//            object : Callback<ArrayList<ItemEx.MyCouponRevResult?>?> {
//                override fun onResponse(
//                    call: Call<ArrayList<ItemEx.MyCouponRevResult?>?>,
//                    response: Response<ArrayList<ItemEx.MyCouponRevResult?>?>
//                ) {
//                    if (response.isSuccessful) {
//                        listener.onComplete(response.body(), call.request().url().toString())
//                    } else {
//                        listener.onFail(response.code(), call.request().url().toString())
//                    }
//                }
//
//                override fun onFailure(
//                    call: Call<ArrayList<ItemEx.MyCouponRevResult?>?>,
//                    t: Throwable
//                ) {
//                    listener.onFail(t.toString(), call.request().url().toString())
//                }
//            })
//    }
}