<template>
  <v-container fluid fill-height>
    <nav>
      <div class="logo">
        <!-- <v-avatar size="75"> 
          <img src="@/assets/images/welcome-icon.png" alt="welcome-icon">
        </v-avatar> -->
        <img class="avatar" src="@/assets/images/welcome-icon.png" alt="welcome-icon">
        <p>SSOK SSOK</p>
      </div>
      <div>
        <div class="glow" @click="moveToMainPage"></div>
      </div>
      <!-- <v-btn class="mr-2" icon @click="moveToMainPage"><v-icon color="white" size="75">mdi-google-controller</v-icon></v-btn> -->
    </nav>

    <v-row fluid fill-height align="center" class="mx-7" style="min-height: 85vh;">
      <div style="width: auto;">
        <p>
          {{ user }}
          다양한 언어를
          <br>
          재밌게 배워보아요!
        </p>
        <button v-if="getCheckLogin" class="auth-button" @click="logout">Logout</button>
        <button v-else class="auth-button" @click="google">Google</button>
      </div>
    </v-row>
  </v-container>
  
</template>

<script>
import { GOOGLE_AUTH_URL, ACCESS_TOKEN } from '@/config/index.js';
import { mapState } from 'vuex';
import "@/style/auth-button.scss";
import "@/style/light-button.scss";
export default {
  name: "WelcomePage",
  data () {
    return {
      user: this.userName,
    }
  },
  mounted () {
    console.log("마운티드")
    this.getToken()
  },
  computed: {
    ...mapState('UserStore', ['userName']),
    getCheckLogin () {
      return this.$store.getters["UserStore/getCheckLogin"];
    },
  },
  methods: {
    moveToMainPage: function () {
      this.$router.push({ name: "MainPage" });
    },
    google() {
      window.location.href = GOOGLE_AUTH_URL
    },
    getUrlParameter(name) {
      name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
      var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
      var results = regex.exec(window.location.search);

      return results === null
        ? ""
        : decodeURIComponent(results[1].replace(/\+/g, " "));
    },
    getUser () {
      if (localStorage.getItem(ACCESS_TOKEN)) {
        this.$store.dispatch("UserStore/fetchUser")
        this.$router
          .push({ name: "WelcomePage" })
          .catch(()=>{})
      }
    },
    getToken() {
      const token = this.getUrlParameter("token");
      console.log(token);
      if (token) {
        localStorage.setItem(ACCESS_TOKEN, token)
      }
      this.getUser()
    },
    logout () {
      this.$store.dispatch("UserStore/logout")
    }
  },
};
</script>

<style lang="scss" scoped>
.container {
  padding: 1%;
  nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding:0 2vh;
    height:10vh;
    width: 100%;
    color: white;
    background: none;
    font-size: 1rem;
    .logo{
      display: flex;
      align-items: center;
      width: 30vw;
      .avatar{
        vertical-align: middle;
        border-radius: 50%;
        width: 15%;
        height: 15%;
      }
      p{
        font-size: 5vh;
        margin: 0 0 0 0.5vw
      }
    }
    div{

    }
  }
  .row{
    div{
      text-align: center;
      p{
        font-size: 2.5rem;
        text-align: center;
        margin: 0;
      }
    }
  }
}
</style>
