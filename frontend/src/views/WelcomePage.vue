<template>
  <div>
    <v-container pa-0 fluid fill-height bg-img>
      <v-toolbar 
        style="background-color: rgba( 255, 255, 255, 0 );"
        :elevation="0"
        height="100%"
        width="100%"
      >
        <v-avatar size="75" class="mr-2">
          <img src="@/assets/images/welcome-icon.png" alt="welcome-icon">
        </v-avatar>
        <h1 class="font-color display-2">SSOK SSOK</h1>
        <v-spacer></v-spacer>
        <v-btn class="mr-2" icon @click="moveToMainPage"><v-icon color="white" size="75">mdi-google-controller</v-icon></v-btn>
      </v-toolbar>

      <v-row fluid fill-height align="center" class="mx-7" style="min-height: 85vh;">
        <div style="width: auto;">
          <h1 class="font-color display-3">
            다양한 언어를
            <br>
            재밌게 배워보아요!
          </h1>
          <v-btn @click.prevent="google"></v-btn>
          <!-- <v-btn class="google_login_btn">
            <GoogleLogin
              :params="params"
              :onSuccess="GoogleLoginSuccess"
              :renderParams="renderParams"
            />
          </v-btn> -->
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import { mapActions } from 'vuex';
// import GoogleLogin from 'vue-google-login';
// import GoogleLoginBtn from 'vue-google-signin-button-directive';
import { GOOGLE_AUTH_URL } from '@/config/index.js';
export default {
  name: "WelcomePage",
  directives: {
    // GoogleLoginBtn,
  },
  data: function () {
    return {
      // title: 'error',
      // content: '로그인 실패!',
      // alert: false,
      // loading: false,
      // email: '',
      // pass: '',
      // params: {
      //   client_id: '943641916006-me9dbk59g6gkdiha6oke6cjl2g6fjkap.apps.googleusercontent.com',
      // },
      // //Google Ui 사용하여 Login 버튼 만드는 것
      // renderParams: {
      //   width: 250,
      //   height: 50,
      //   longtitude: true,
      // }
    }
  },
  components: {
    // GoogleLogin,
  },
  //currentUser가 없으면, 가져오기
  created() {
    let currentUser = localStorage.getItem("currentUser");
    if (!currentUser || currentUser === {}) {
      this.$store.dispatch("fetchUser");
    }
  },
  //로그인 여부 판단하기
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    }
  },
  methods: {
    // ...mapActions(['googleOauth']),
    moveToMainPage: function () {
      this.$router.push({ name: "MainPage" });
    },
    google: function () {
      window.location.href = GOOGLE_AUTH_URL;  
    }
    // // 구글로그인
    // async GoogleLoginSuccess(googleUser) {
    //   this.loading = true;
    //   const profile = googleUser.getBasicProfile();
    //   const result = await this.googleOauth({
    //     email: googleUser.getBasicProfile().getEmail(),
    //     pass: null,
    //     image: profile.getImageUrl(),
    //     token: googleUser.getAuthResponse().id_token,
    //     name: profile.getName(),
    //   });
    //   this.loading = false;
    //   if (!result) {
    //     this.title = 'error';
    //     this.content = '로그인 실패!';
    //     this.alert = true;
    //   } else {
    //     this.email = '';
    //     this.pass = '';
    //   }
    // },
  },
};
</script>

<style>

</style>
