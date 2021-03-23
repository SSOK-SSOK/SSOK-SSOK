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
          <div class="google_login_btn">
            <GoogleLogin
              :params="params"
              :onSuccess="GoogleLoginSuccess"
              :renderParams="renderParams"
            />
          </div>
        </div>
      </v-row>
    </v-container>
  </div>
  
</template>

<script>
import { mapActions } from 'vuex';
import GoogleLogin from 'vue-google-login';
import GoogleLoginBtn from 'vue-google-signin-button-directive';
export default {
  name: "WelcomePage",
  directives: {
    GoogleLoginBtn,
  },
  data: function () {
    return {
      params: {
        cliend_id: 'please fill this params',
      },
      //Google Ui 사용하여 Login 버튼 만드는 것
      renderParams: {
        width: 250,
        height: 50,
        longtitude: true,
      }
    }
  },
  components: {
    GoogleLogin,
  },
  methods: {
    ...mapActions(['GOOGLE_OAUTH']),
    moveToMainPage: function () {
      this.$router.push({ name: "MainPage" });
    },
    // 구글로그인
    async GoogleLoginSuccess(googleUser) {
      this.loading = true;
      const profile = googleUser.getBasicProfile();
      const result = await this.GOOGLE_OAUTH({
        email: googleUser.getBasicProfile().getEmail(),
        pass: null,
        image: profile.getImageUrl(),
        token: googleUser.getAuthResponse().id_token,
        name: profile.getName(),
      });
      this.loading = false;
      if (!result) {
        this.title = 'error';
        this.content = '로그인 실패!';
        this.alert = true;
      } else {
        this.email = '';
        this.pass = '';
      }
    },
  },
};
</script>

<style>

</style>
