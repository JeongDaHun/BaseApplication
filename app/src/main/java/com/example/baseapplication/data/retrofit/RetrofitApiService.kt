package com.example.baseapplication.data.retrofit

import com.example.baseapplication.data.model.LottoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    //로도 정보 검색
    @GET("/common.do?method=getLottoNumber")
    fun getLottoInfo(@Query("drwNo") drwNo: Int): Call<LottoModel>

//    //지점목록검색
//    @get:POST(config.Conn.BRANCH_SEARCH_URL)
//    val branchList: Call<ArrayList<Any?>?>?
//
//    //메인화면 지점명 및 문자건수 수신
//    @POST(config.Conn.MAIN_MESSAGE_CNT_URL)
//    fun getMsgCntList(
//        @Query("clientcode") clientcode: String?,
//        @Query("personcode") personcode: String?
//    ): Call<ArrayList<ItemEx.MsgCnt?>?>?
//
//    // @Query("push_id") String push_id); //[20.01.16] 로그인 안한 고객에게 push 보내기
//    //홍보문자리스트
//    @POST(Conn.MAIN_PUSH_MSG_URL)
//    fun getPushMsgList(@Query("personcode") personcode: String?): Call<ArrayList<ItemEx.PushMsg?>?>?
//
//    //@Query("clientcode") String clientcode,
//    //@Query("push_id") String push_id); //[20.01.16] 로그인 안한 고객에게 push 보내기
//    //홍보문자 상세보기
//    @POST(Conn.MAIN_PUSH_DETAIL_URL)
//    fun getPushDetailList(
//        @Query("clientcode") clientcode: String?,
//        @Query("personcode") personcode: String?,
//        @Query("msgcode") msgcode: String?
//    ): Call<ArrayList<ItemEx.PushDetail?>?>?
//
//    // @Query("push_id") String push_id); //[20.01.16] 로그인 안한 고객에게 push 보내기
//    //홍보문자 확인여부 회신(포인트지급)
//    @POST(Conn.MAIN_PUSH_RECEIVE_URL)
//    fun getPushReceive(
//        @Query("clientcode") clientcode: String?,
//        @Query("savezonecode") savezonecode: String?,
//        @Query("personcode") personcode: String?,
//        @Query("msgcode") msgcode: String?,
//        @Query("recv_point") recv_point: String?,
//        @Query("msgtitle") msgtitle: String?,
//        @Query("url") url: String?
//    ): Call<ArrayList<ItemEx.PushReceive?>?>?
//
//    //메인화면 배너수신
//    @POST(config.Conn.BANNER_RECEIVE_URL)
//    fun getBannerList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.MainBanner?>?>?
//
//    //쇼핑뉴스 - HOT
//    @POST(Conn.SHOPPING_NEWS_HOT_URL)
//    fun getShoppingHotList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.ShoppingHotList?>?>?
//
//    //식품관 리스트
//    @POST(Conn.SHOPPING_FOOD_LIST_URL)
//    fun getShoppingFoodList(
//        @Query("clientcode") clientcode: String?,
//        @Query("foodkey") foodkey: String?
//    ): Call<ArrayList<ItemEx.ShoppingFoodList?>?>?
//
//    //식품관 상세내역
//    @POST(Conn.SHOPPING_FOOD_DETAIL_URL)
//    fun getShoppingFoodDetail(
//        @Query("clientcode") clientcode: String?,
//        @Query("foodkey") foodkey: String?,
//        @Query("menu_code") menu_code: String?
//    ): Call<ArrayList<ItemEx.ShoppingFoodDetail?>?>?
//
//    //패션관 리스트
//    @POST(Conn.SHOPPING_FASHION_LIST_URL)
//    fun getShoppingFashionList(
//        @Query("clientcode") clientcode: String?,
//        @Query("tab_gn") tag_gn: String?
//    ): Call<ArrayList<ItemEx.ShoppingFashionList?>?>?
//
//    //쇼핑뉴스 패션관 상세내역
//    @POST(Conn.SHOPPING_FASHION_DETAIL_URL)
//    fun getShoppingFashionDetail(
//        @Query("clientcode") clientcode: String?,
//        @Query("tab_gn") tab_gn: String?,
//        @Query("menu_gn") menu_gn: String?
//    ): Call<ArrayList<ItemEx.ShoppingFashionDetail?>?>?
//
//    //브랜드관 리스트
//    @POST(Conn.SHOPPING_BRAND_LIST_URL)
//    fun getShoppingBrandList(
//        @Query("clientcode") clientcode: String?,
//        @Query("tab_gn") tag_gn: String?
//    ): Call<ArrayList<ItemEx.ShoppingFashionList?>?>?
//
//    //쇼핑뉴스 브랜드관 업체리스트
//    @POST(Conn.SHOPPING_BRANDSHOP_LIST_URL)
//    fun getBrandList(
//        @Query("clientcode") clientcode: String?,
//        @Query("tab_gn") tab_gn: String?,
//        @Query("menu_gn") menu_gn: String?
//    ): Call<ArrayList<ItemEx.BrandList?>?>?
//
//    //브랜드관 상세내역
//    @POST(Conn.SHOPPING_BRAND_DETAIL_URL)
//    fun getShoppingBrandDetail(
//        @Query("clientcode") clientcode: String?,
//        @Query("tab_gn") tag_gn: String?,
//        @Query("newskey") newskey: String?
//    ): Call<ArrayList<ItemEx.ShoppingBrandDetail?>?>?
//
//    //브랜드검색 리스트
//    @POST(Conn.SHOPPING_BRAND_FIND_URL)
//    fun getShoppingBrandFind(
//        @Query("clientcode") clientcode: String?,
//        @Query("clientname") clientname: String?
//    ): Call<ArrayList<ItemEx.ShoppingBrandFind?>?>?
//
//    //지점별 층별 리스트
//    @POST(config.Conn.LAYER_INFO_URL)
//    fun getLayerList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.LayerInfo?>?>?
//
//    //지점별 층별 상세 정보
//    @POST(config.Conn.LAYER_DETAIL_INFO_URL)
//    fun getLayerDetailList(
//        @Query("clientcode") clientcode: String?,
//        @Query("layer") layer: String?
//    ): Call<ArrayList<ItemEx.LayerDetailInfo?>?>?
//
//    //[19.12.13] 층별검색
//    @POST(config.Conn.LAYER_SEARCH_URL)
//    fun getLayerSearch(
//        @Query("clientcode") clientcode: String?,
//        @Query("layer") layer: String?,
//        @Query("clientname") clientname: String?
//    ): Call<ArrayList<ItemEx.LayerSearchInfo?>?>?
//
//    //전단지 조회
//    @POST(config.Conn.PAPER_SEARCH_URL)
//    fun getPaperList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.PaperInfo?>?>?
//
//    //회원가입 및 수정 - 0:성공, 99:등록된 전화번호가 없습니다. -1:가입승인 저장프로시져 오류
//    @POST(config.Conn.MEMBER_JOIN_URL)
//    fun reqJoin(
//        @Query("personcode") personcode: String?,  //회원가입일땐 "", 고객정보 수정일땐 personcode 입력해야 함.
//        @Query("personname") personname: String?,
//        @Query("persontel") persontel: String?,
//        @Query("personbirth") personbirth: String?,
//        @Query("merry") marry: String?,  //결혼기념일
//        @Query("personsex") personsex: String?,  //M=남자, F=여
//        @Query("clientcode") clientcode: String?,
//        @Query("savezonecode") savezonecode: String?,
//        @Query("phonegubun") phonegubun: String?,  //android, ios
//        @Query("edit_site") edit_site: String?,  //APP, POS
//        @Query("dm_yn") dm_yn: String?,
//        @Query("sms_yn") sms_yn: String?,
//        @Query("pushyn") pushyn: String?,  //무조건  Y
//        @Query("cpost") cpost: String?,  //우편번호
//        @Query("personjuso") personjuso: String?,
//        @Query("personbunji") personbunji: String?,
//        @Query("j_juso") j_juso: String?,  //구 지번주소
//        @Query("postkey") postkey: String?,  //건물관리번호
//        @Query("member_type") member_type: String?,  //신규:0, 기존회원:1
//        @Query("push_id") push_id: String?,
//        @Query("joingubun") joingubun: String?,  //B:세이브존(=default)
//        @Query("url") url: String?
//    ): Call<ArrayList<ItemEx.MemberReturnInfo?>?>?
//
//    //회원탈퇴
//    @POST(Conn.MEMBER_LEAVE_URL)
//    fun reqMemberLeave(
//        @Query("personcode") personcode: String?,
//        @Query("leave_yn") leave_yn: String?,
//        @Query("url") url: String?
//    ): Call<ArrayList<ItemEx.MemberLeaveInfo?>?>?
//
//    //기존회원 체크
//    @POST(Conn.MEMBER_EXIST_CHK_URL)
//    fun reqMemberExist(
//        @Query("kor_name") kor_name: String?,
//        @Query("mtel") mtel: String?,
//        @Query("birthday") birthday: String?,
//        @Query("sexgbn") sexgbn: String?
//    ): Call<ArrayList<ItemEx.MemberExistInfo?>?>?
//
//    //기존회원 리스트
//    @POST(Conn.MEMBER_EXIST_LIST_URL)
//    fun reqMemberExistList(
//        @Query("kor_name") kor_name: String?,
//        @Query("mtel") mtel: String?,
//        @Query("birthday") birthday: String?,
//        @Query("sexgbn") sexgbn: String?
//    ): Call<ArrayList<ItemEx.MemberExistInfoList?>?>?
//
//    //고객정보 검색
//    @POST(Conn.MEMBER_REQINFO_URL)
//    fun reqMemberInfo(@Query("personcode") personcode: String?): Call<ArrayList<ItemEx.MemberInfo?>?>?
//
//    //로그인
//    @POST(Conn.PERSON_LOGIN_URL)
//    fun reqLogin(
//        @Query("personname") personname: String?,
//        @Query("persontel") persontel: String?
//    ): Call<ArrayList<ItemEx.LoginInfo?>?>?
//
//    //토큰 갱신
//    @POST(Conn.TOKEN_CHANGE_URL)
//    fun changeToken(
//        @Query("personcode") personcode: String?,
//        @Query("push_id") push_id: String?,
//        @Query("phonegubun") phonegubun: String?
//    ): Call<ArrayList<ItemEx.TokenResp?>?>? //android, ios
//
//    //쿠폰 리스트
//    @POST(Conn.COUPON_LIST_URL)
//    fun getCouponList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.CouponInfo?>?>?
//
//    //이벤트 리스트
//    @POST(Conn.EVENT_LIST_URL)
//    fun getEventList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.EventList?>?>?
//
//    //더보기 사이드 메뉴
//    @POST(Conn.MORE_URL)
//    fun getMoreInfo(
//        @Query("clientcode") clientcode: String?,
//        @Query("personcode") personcode: String?
//    ): Call<ArrayList<ItemEx.MoreInfo?>?>?
//
//    //MY 포인트
//    @POST(Conn.MYPOINT_URL)
//    fun getMyPoint(@Query("personcode") personcode: String?): Call<ArrayList<ItemEx.MyPoint?>?>?
//
//    //MY 포인트 리스트
//    @POST(Conn.MYPOINT_LIST_URL)
//    fun getMyPointList(
//        @Query("personcode") personcode: String?,
//        @Query("find_month") find_month: String?,  //default=1
//        @Query("display_gbn") display_gbn: String? //0:전체, 1:가산포인트, 2:사용포인트
//    ): Call<ArrayList<ItemEx.MyPointList?>?>?
//
//    //공지사항 리스트
//    @POST(Conn.NOTICE_LIST_URL)
//    fun getNoticeList(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.NoticeList?>?>?
//
//    //공지사항 상세내역
//    @POST(Conn.NOTICE_DETAIL_URL)
//    fun getNoticeDetail(
//        @Query("notice_year") notice_year: String?,
//        @Query("notice_num") notice_num: String?
//    ): Call<ArrayList<ItemEx.NoticeDetail?>?>?
//
//    //Push on/off change
//    @POST(Conn.PUSH_ONOFF_URL)
//    fun setPushChange(
//        @Query("personcode") personcode: String?,
//        @Query("pushyn") pushyn: String?
//    ): Call<ArrayList<ItemEx.PushOnoffResult?>?>?
//
//    //회원탈퇴 체크
//    @POST(Conn.LEAVE_CHECK_URL)
//    fun checkLeave(@Query("personcode") personcode: String?): Call<ArrayList<ItemEx.LeaveCheckResult?>?>?
//
//    //APP VERSION
//    @get:POST(Conn.APP_VERSION)
//    val appVersion: Call<ArrayList<Any?>?>?
//
//    //POPUP INFO
//    @POST(Conn.POPUP_INFO)
//    fun getPopupInfo(@Query("clientcode") clientcode: String?): Call<ArrayList<ItemEx.PopupResult?>?>?
//
//    //[19.12.02] 추천인 기간 여부
//    @get:POST(Conn.NOMINATE_INFO)
//    val nominateInfo: Call<ArrayList<Any?>?>?
//
//    //[19.12.06] 추천인 포함 회원가입
//    @POST(Conn.JOIN_NOMINATE)
//    fun reqJoinNominate(
//        @Query("personcode") personcode: String?,  //회원가입일땐 "", 고객정보 수정일땐 personcode 입력해야 함.
//        @Query("personname") personname: String?,
//        @Query("persontel") persontel: String?,
//        @Query("personbirth") personbirth: String?,
//        @Query("merry") marry: String?,  //결혼기념일
//        @Query("personsex") personsex: String?,  //M=남자, F=여
//        @Query("clientcode") clientcode: String?,
//        @Query("savezonecode") savezonecode: String?,
//        @Query("phonegubun") phonegubun: String?,  //android, ios
//        @Query("edit_site") edit_site: String?,  //APP, POS
//        @Query("dm_yn") dm_yn: String?,
//        @Query("sms_yn") sms_yn: String?,
//        @Query("pushyn") pushyn: String?,  //무조건  Y
//        @Query("cpost") cpost: String?,  //우편번호
//        @Query("personjuso") personjuso: String?,
//        @Query("personbunji") personbunji: String?,
//        @Query("j_juso") j_juso: String?,  //구 지번주소
//        @Query("postkey") postkey: String?,  //건물관리번호
//        @Query("member_type") member_type: String?,  //신규:0, 기존회원:1
//        @Query("push_id") push_id: String?,
//        @Query("joingubun") joingubun: String?,  //B:세이브존(=default)
//        @Query("url") url: String?,
//        @Query("nominate_num") nominate_num: String?,  //추천인 코드
//        @Query("nominate_tel") nominate_tel: String? //추천인 전화번호
//    ): Call<ArrayList<ItemEx.MemberReturnInfo?>?>?
//
//    //[20.01.13] 앱설치고객 고객정보관리(앱설치 후 지점선택 및 선호지점 수정시)
//    @POST(Conn.APP_INSTALL_MANAGE)
//    fun appInstallManage(
//        @Query("newtel") newtel: String?,
//        @Query("personcode") personcode: String?,
//        @Query("clientcode") clientcode: String?,
//        @Query("phonegubun") phonegubun: String?,
//        @Query("pushyn") pushyn: String?,
//        @Query("push_id") push_id: String?
//    ): Call<ArrayList<ItemEx.AppInstallInfo?>?>?
//
//    //MY쿠폰 상품 쿠폰 리스트
//    @POST(Conn.SAVEZONE_MY_COUPON_URL)
//    fun getMyCouponList(@Query("tel") tel: String?): Call<ArrayList<ItemEx.MyCouponInfo?>?>?
//
//    //MY쿠폰 상품 쿠폰 지급
//    @POST(Conn.SAVEZONE_MY_COUPON_REV_URL)
//    fun reqMyCouponRev(
//        @Query("win_key") win_key: String?,
//        @Query("personcode") personcode: String?
//    ): Call<ArrayList<ItemEx.MyCouponRevResult?>?>?
}