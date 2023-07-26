let list = new Vue({
    el: "#houseList",
    data() {
        return {
            items: [
                {
                    id: 0,
                    house_no: '30865826521016',
                    image: 'image/img/house1.3.jpg',
                    address: '桂林理工大学罗宗樱大红床',
                    type: 1,
                    area: '35',
                    realname: '李耀松',
                    addressLink: '#',
                    mph: '一号组团',
                    house_add: '配套',
                    toward: '朝向',
                    basemoney: 0
                },
            ],
            id: 0
        }
    },
    mounted() {
        this.getHouse();
    },
    methods: {
        getHouse() {
            //首页展示
            axios({
                url: "/house/getHouseList",
                method: "post",
                data: {
                    id: this.id
                },
            }).then(resp => {
                if(resp.data.code == 1){
                    this.items = resp.data.data
                    for (let index = 0; index < this.items.length; index++) {
                        this.items[index].image = `/common/download?name=${this.items[index].image}`
                    }
                }else {
                    console.log(resp)
                    this.$message.error(resp.data.msg)
                }
            })
        }
    }
})

//进行钱分区搜索
new Vue({
    el: "#searchByMoney",
    data() {
        return {
            fromPrice: '',
            toPrice: ''
        }
    },
    methods: {
        getPriceRange(range) {
            // 根据range参数发送请求到后台
            // 使用Axios或其他HTTP客户端库发送请求
            axios({
                url: "/house/getHouseList",
                method: "post",
                data: {range: range}
            }).then(resp => {
                if(resp.data.code == 1){
                    list.items = resp.data.data;
                    for (let index = 0; index < list.items.length; index++) {
                        list.items[index].image = `/common/download?name=${list.items[index].image}`
                    }
                }else {
                    console.log(resp)
                    this.$message.error(resp.data.msg)
                }
            })
        }
    }
})

//房子类型分区搜索
new Vue({
    el: "#getType",
    methods: {
        getTypeList(type) {
            // 根据range参数发送请求到后台
            // 使用Axios或其他HTTP客户端库发送请求
            axios({
                url: "/house/getHouseList",
                method: "post",
                data: {type: type}
            }).then(resp => {
                if(resp.data.code == 1){
                    list.items = resp.data.data;
                    for (let index = 0; index < list.items.length; index++) {
                        list.items[index].image = `/common/download?name=${list.items[index].image}`
                    }
                }else {
                    console.log(resp)
                    this.$message.error(resp.data.msg)
                }

            })
        }
    }
})

//进行朝向分区搜索
new Vue({
    el: "#getToword",
    methods: {
        getToword(toward) {
            // 根据range参数发送请求到后台
            // 使用Axios或其他HTTP客户端库发送请求
            axios({
                url: "/house/getHouseList",
                method: "post",
                data: {toward: toward}
            }).then(resp => {
                if(resp.data.code == 1){
                    list.items = resp.data.data;
                    for (let index = 0; index < list.items.length; index++) {
                        list.items[index].image = `/common/download?name=${list.items[index].image}`
                    }
                }else {
                    console.log(resp)
                    this.$message.error(resp.data.msg)
                }

            })
        }
    }
})

new Vue({
    el: "#tabs",
    data() {
        return {
            tabs: [
                {name: '实拍真房', link: '#'},
                {name: '经纪人房源', link: '#'},
                {name: '个人房源', link: '#'},
            ]
        };
    }
})

