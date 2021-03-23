<template>
  <div>
    <!-- <v-toolbar 
      color="#040431"
      :elevation="0"
      height="100%"
    >
      <v-avatar size="75" class="mr-2">
        <img src="@/assets/images/welcome-icon.png" alt="welcome-icon">
      </v-avatar>
      <h1 class="font-color">SSOK SSOK</h1>
      <v-spacer></v-spacer>
      <v-btn class="mr-2" icon @click="moveToMainPage"><v-icon color="white" size="75">mdi-google-controller</v-icon></v-btn>
    </v-toolbar>

    <div style="height: 100%;">
      dd
    </div> -->

      <div class="google_login_btn">
        <GoogleLogin
          :params="params"
          :onSuccess="GoogleLoginSuccess"
          :renderParams="renderParams"
        />
      </div>

    <!-- <v-card
      class="d-flex align-center"
      color="#040431"
      :elevation="0"
      max-width="70%"
    >
      <v-list-item-content>
        <v-list-item-title class="headline mb-1 font-color">
          다양한 언어를
          <br>
          재밌게 배워보아요!
        </v-list-item-title>
      </v-list-item-content>
      <v-card-actions>
        <v-btn
          outlined
          rounded
          text
          color="white"
        >
          Button
        </v-btn>
      </v-card-actions>
    </v-card> -->
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
