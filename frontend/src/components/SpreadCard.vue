<template>
  <div class="category-cards">
    <div class="perspective">
      <div class='card-container'>
        <div class='card card-card1'>
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
          </div>
        </div>
        <div class='card card-card2'>
          <div class='card__face card__face--top'>
            <span class='card__value'>
              Q
            </span>
            <span class='card__suit'>
              <svg width="21px" height="18px" viewBox="0 0 21 18" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                  <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                      <circle id="Oval" stroke="#635F5C" cx="4.5" cy="13.5" r="4"></circle>
                      <circle id="Oval" stroke="#635F5C" cx="10.5" cy="4.5" r="4"></circle>
                      <circle id="Oval" stroke="#635F5C" cx="16.5" cy="13.5" r="4"></circle>
                  </g>
              </svg>
            </span>
          </div>
          <div class='card__face card__face--btm'>
            <span class='card__value'>
              Q
            </span>
            <span class='card__suit'>
              <svg width="21px" height="18px" viewBox="0 0 21 18" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                  <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                      <circle id="Oval" stroke="#635F5C" cx="4.5" cy="13.5" r="4"></circle>
                      <circle id="Oval" stroke="#635F5C" cx="10.5" cy="4.5" r="4"></circle>
                      <circle id="Oval" stroke="#635F5C" cx="16.5" cy="13.5" r="4"></circle>
                  </g>
              </svg>
            </span>
          </div>
        </div>
        <div class='card card-card3'>
          <div class='card__face card__face--top'>
            <span class='card__value'>
              Fruit
            </span>
          </div>
          <div class='card__face card__face--btm'>
            <span class='card__value'>
              Fruit
            </span>
            <span class='card__suit'>
              <i class="far fa-lemon"></i>
            </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SpreadCard',
  data() {
    return {
      
    };
  },
  mounted() {
    
  },
  methods: {
    
  },
};
</script>

<style lang="scss" scoped>
.category-cards{
  width: 100%;
  height: 100%;
}
.perspective {
  height: 100%;
  perspective: 2000px;
  position: absolute;
  width: 100%;
}
.card-container {
	cursor: pointer;
	left: 30%;
	position: absolute;
	top: 50%;
	transform: rotateX(35deg) translate3d(-50%,-50%,0);
	height: 45vw;
	width: 32vw;
	max-height: 450px;
	max-width: 320px;
  .card-card1 {
    transform: rotate(-17deg) translate3d(-23%,-5%,0);
    box-shadow: 0 1px 2px 0 rgba(151,150,146,0.4), 0 2px 6px 0 rgba(151,150,146,0.4) W;
  }
  .card-card2 {
    transform: rotate(-10deg) translate3d(-10%,-8%,0);
    box-shadow: 0 1px 2px 0 rgba(151,150,146,0.4), 0 2px 4px 0 rgba(151,150,146,0.4);
  }
  .card-card3 {
    transform: rotate(0deg) translate3d(0%,-8%,0);
    box-shadow: 0 1px 2px 0 rgba(151,150,146,0.4), 0 2px 4px 0 rgba(151,150,146,0.4);
  }
}



.card {
	background: white;
	border-radius: 16px;
	box-shadow: 0 1px 2px 0 rgba(151,150,146,0.4);
	height: 45vw;
	max-height: 450px;
	max-width: 320px;
	min-height: 225px;
	min-width: 160px;
	position: absolute;
	width: 32vw;
}
.card-card1 {
	color: #a68385;
	.card__value {
		transform: translatex(-2px);
	}
	.card__suit {
		margin-top: 0.8rem;
	}
}
.card-card2 {
	color: #635f5c;
	.card__suit {
		margin-top: 0.8rem;
	}
}
.card-card3 {
  background-image: url('https://acegif.com/wp-content/gifs/apple-8.gif');
  background-size: 150%;
  background-position: 50%;
	color: #a68385;
	.card__suit {
		margin-top: 0.7rem;
	}
}
.card__face {
	padding: 1.8rem 1.6rem;
	position: absolute;
}
.card__face--btm {
	bottom: 0;
	right: 0;
	transform: rotate(180deg);
}
.card__value {
	display: block;
	text-align: center;
	width: 22px;
}
.card__suit {
	display: block;
	margin-top: 0.5rem;
}



      // // background-image: url('https://i.pinimg.com/originals/d6/3c/de/d63cded9d8454a5b57ee8bef31c3ee71.gif');
      // background-image: url('https://acegif.com/wp-content/gifs/apple-8.gif');
      // background-size: 150%;
      // background-position: 50%;
</style>