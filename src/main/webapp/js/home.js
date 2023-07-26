new Vue({
    el: '#slider',
    data() {
        return {
            slides: [
                {image: 'image/img/house1.1.jpg', title: "老表住房，无所不能"},
                {image: 'image/img/house1.2.jpg', title: "老表住房，无所不能"},
            ],
            currentIndex: 0,
            timerId: null
        }
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
            const searchParams = new URLSearchParams(window.location.search);
            const id = searchParams.get('id');
            axios({
                url: "/house/getHouseImageById?id=" + id,
                method: "get"
            }).then(response => {
                if (response.data.code == 1) {
                    this.slides = response.data.data;
                    console.log(this.slides)
                    //进行图片处理
                    for (let i = 0; i < this.slides.length; i++) {
                        this.slides[i].image = `/common/download?name=${this.slides[i].image}`
                    }
                } else {
                    this.$message.error(response.data.msg)
                }
                this.startAutoPlay();
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