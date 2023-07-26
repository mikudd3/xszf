new Vue({
    el: '#slider',
    data() {
        return {
            slides: [
                {url: '/image/img/house1.1.jpg', title: '来感受最舒服的环境'},
                {url: '/image/img/house1.2.jpg', title: '这是你最喜爱的地方'},
            ],
            currentIndex: 0,
            timerId: null
        };
    },
    computed: {
        currentSlide() {
            return this.slides[this.currentIndex];
        }
    },
    mounted() {
        // this.startAutoPlay();
        this.fetchSlides();
    },
    methods: {
        fetchSlides() {
            axios({
                url: "/house/getlbHouse",
                method: "get"
            }).then(response => {
                if(response.data.code == 1){
                    let r = response.data;
                    this.slides = r.data;
                    //进行图片处理
                    for (let i = 0; i < this.slides.length; i++) {
                        this.slides[i].url = `/common/download?name=${this.slides[i].url}`
                    }
                    console.log(r.data)
                    this.startAutoPlay();
                }else {
                    console.log(response)
                    this.$message.error(response.data.msg)
                }
            }).catch(error => {
                console.error('Error fetching slides:', error);
            });
        },
        nextSlide() {
            this.currentIndex = (this.currentIndex + 1) % this.slides.length;
        },
        prevSlide() {
            this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
        },
        startAutoPlay() {
            this.timerId = setInterval(() => {
                this.nextSlide();
            }, 1500);
        },
        stopAutoPlay() {
            clearInterval(this.timerId);
        }
    },
    beforeDestroy() {
        this.stopAutoPlay();
    }
})