<template>
  <v-container fluid fill-height>
    <div class="background">
      <div id="stars"></div>
      <div id="stars2"></div>
      <div id="stars3"></div>
    </div>
    <div class="contents">
      <nav>
        <div class="logo">
          <img
            class="avatar"
            src="@/assets/images/welcome-icon.png"
            alt="welcome-icon"
          />
          <p>SSOK SSOK</p>
        </div>
        <button v-if="getCheckLogin" class="auth-button" @click="logout">Logout</button>
        <button v-else class="auth-button" @click="google">Google</button>
      </nav>
      <v-row>
        <div class="loginZone col-md-6 col-sm-12">
          <div>
            <p>
              {{ user }}
              다양한 언어를
              <br />
              재미있게 배워보아요!
            </p>
            <button @click="moveMainPage" class="gold-button">게임 시작</button>
          </div>
        </div>
        <div class="cosmosZone col-md-6 col-sm-0">
          <Cosmos/>
        </div>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import Cosmos from "@/components/Cosmos.vue";
import { GOOGLE_AUTH_URL, ACCESS_TOKEN } from "@/config/index.js";
import { mapState } from "vuex";
export default {
  name: "WelcomePage",
  components: {
    Cosmos,
  },
  data() {
    return {
      user: this.userName,
    }
  },
  computed: {
    ...mapState("UserStore", ["userName"]),
    getCheckLogin() {
      return this.$store.getters["UserStore/getCheckLogin"];
    },
  },
  mounted() {
    console.log("마운티드");
    this.getToken();
  },
  methods: {
    moveMainPage () {
      this.$router.push({ name: "MainPage" });
    },
    google() {
      window.location.href = GOOGLE_AUTH_URL;
    },
    getUrlParameter(name) {
      name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
      var regex = new RegExp("[\\?&]" + name + "=([^&#]*)");
      var results = regex.exec(window.location.search);

      return results === null
        ? ""
        : decodeURIComponent(results[1].replace(/\+/g, " "));
    },
    getUser() {
      if (localStorage.getItem(ACCESS_TOKEN)) {
        this.$store.dispatch("UserStore/fetchUser");
        this.$router.push({ name: "WelcomePage" }).catch(() => {});
      }
    },
    getToken() {
      const token = this.getUrlParameter("token");
      console.log(token);
      if (token) {
        localStorage.setItem(ACCESS_TOKEN, token);
      }
      this.getUser();
    },
    logout() {
      this.$store.dispatch("UserStore/logout");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "@/style/star.sass";
@import "@/style/auth-button.scss";
@import "@/style/gold-button.scss";

.container {
  padding: 1%;
  .background {
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
  }
  .contents {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 10;
    left: 0;
    padding: 1%;
    nav {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 2%;
      height: 10%;
      width: 100%;
      color: white;
      background: none;
      .logo {
        display: flex;
        align-items: center;
        width: 70%;
        padding: 0;
        .avatar {
          vertical-align: middle;
          border-radius: 50%;
          width: 3em;
        }
        p {
          font-size: 2em;
          margin: 0 0 0 0.5vw;
        }
      }
    }
    .row{
      width: 100%;
      height: 90%;
      margin: 0;
      .loginZone {
        height: 100%;
        padding: 0;
        // position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
        div {
          text-align: center;
          p {
            font-size: 2.8em;
            text-align: center;
            margin: 0 0 5% 0;
          }
          //gold-button.scss
        }

      }
      .cosmosZone{
        height: 100%;
        padding: 0;
      }
    }
  }
}
</style>
