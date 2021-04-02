<template>
  <v-card
    class="mx-auto"
    max-width="344"
  >
    <v-img
      :src=cardImg
      height="200px"
    ></v-img>

    <v-card-title>
      {{ cardAnswer }}
    </v-card-title>

    <v-card-subtitle>
      1,000 miles of wonder
    </v-card-subtitle>
  </v-card>
  <!-- <div class="card">
    <input type="checkbox" id="card1" class="more" aria-hidden="true">
    <div class="content">
      <div class="front">
        <div class="inner">
          <h2>Cozy apartment</h2>
          <label for="card1" class="button" aria-hidden="true">
            Details
          </label>
        </div>
      </div>
      <div class="back">
        <div class="inner">
          <label for="card1" class="button return" aria-hidden="true">
            <i class="fas fa-arrow-left"></i>
          </label>
        </div>
      </div>
    </div>
  </div> -->
  <!-- <div class='card card-card1'>
    <div class='card__face card__face--top'>
      <span class='card__value'>
        K
      </span>
      <span class='card__suit'>
        <svg width="20px" height="21px" viewBox="0 0 20 21" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                <rect id="Rectangle-3" stroke="#A68385" transform="translate(10.035534, 10.071068) rotate(-315.000000) translate(-10.035534, -10.071068) " x="3.43933983" y="3.47487373" width="13.1923882" height="13.1923882"></rect>
            </g>
        </svg>
      </span>
    </div>
    <div class='card__face card__face--btm'>
      <span class='card__value'>
        K
      </span>
      <span class='card__suit'>
        <svg width="20px" height="21px" viewBox="0 0 20 21" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                <rect id="Rectangle-3" stroke="#A68385" transform="translate(10.035534, 10.071068) rotate(-315.000000) translate(-10.035534, -10.071068) " x="3.43933983" y="3.47487373" width="13.1923882" height="13.1923882"></rect>
            </g>
        </svg>
      </span>
    </div> -->
  </div>
</template>

<script>
// import { mapState } from 'vuex';
export default {
  name: 'QuizCard',
  components: {
  },
  props: {
    currentQuiz: Object
  },
  data() {
    return {
      // cardImg: this.currentQuiz.path,
      // cardAnswer: this.currentQuiz.word,
    };
  },
  computed: {
    cardImg: function () {
      return this.currentQuiz.path
    },
    cardAnswer: function () {
      return this.currentQuiz.word
    }
  },
  watch: {
  },
  created() {
  },
  methods: {
  },
}
</script>

<style lang="scss" scoped>
@import "@/style/spread-card.scss";
$accent-color: #355cc9;

* {
	box-sizing: border-box;
}

.card {
	width: 420px;
	height: 340px;
	margin: 1em;
	perspective: 1500px;
	.content {
		position: relative;
		width: 100%;
		height: 100%;
		transform-style: preserve-3d;
		transition: transform 0.8s cubic-bezier(0.75, 0, 0.85, 1);
	}
}

.more {
	display: none;

	&:checked ~ .content {
		transform: rotateY(180deg);
	}
}

.front,
.back {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	backface-visibility: hidden;
	transform-style: preserve-3d;
	border-radius: 6px;

	.inner {
		height: 100%;
		display: grid;
		padding: 1.5em;
		transform: translateZ(80px) scale(0.94);
	}
}

.front {
	background-color: #fff;
	background-size: cover;
	background-position: center center;

	.inner {
		grid-template-rows: 5fr 1fr 1fr 2fr 1fr;
		justify-items: center;
	}

	h2 {
		grid-row: 2;
		margin-bottom: 0.3em;
		text-transform: uppercase;
		letter-spacing: 3px;
		color: #fff;
		font-weight: 500;
		text-shadow: 0 0 6px rgba(0, 0, 0, 0.1);
	}
}

.back {
	transform: rotateY(180deg);
	background-color: #fff;
	border: 2px solid rgb(240, 240, 240);
	.inner {
		grid-template-rows: 1fr 2fr 1fr 2fr 14fr 1fr 1fr;
		grid-template-columns: repeat(4, auto);
		grid-column-gap: 0.8em;
		justify-items: center;
	}

		i {
			&:before {
				background: linear-gradient(40deg, $accent-color, rgb(67, 138, 243));

				-webkit-text-fill-color: transparent;
				-webkit-background-clip: text;
			}
			font-size: 1.2em;
		}
		.icon {
			margin-left: 0.3em;
			span {
				display: block;
				margin-top: -0.25em;
				font-size: 0.8em;
				font-weight: 600;
				white-space: nowrap;
			}
		}
	}

.button {
	grid-row: -1;
	text-transform: uppercase;
	letter-spacing: 1px;
	font-weight: 600;
	cursor: pointer;
	display: block;
	padding: 0 1.5em;
	height: 3em;
	line-height: 2.9em;
	min-width: 3em;
	background-color: transparent;
	border: solid 2px #fff;
	color: #fff;
	border-radius: 4px;
	text-align: center;
	left: 50%;
	backface-visibility: hidden;
	transition: 0.3s ease-in-out;
	text-shadow: 0 0 6px rgba(0, 0, 0, 0.3);

	&:hover {
		background-color: #fff;
		box-shadow: 0 0 50px rgba(0, 0, 0, 0.5);
		text-shadow: none;
		color: $accent-color;
	}
}

</style>