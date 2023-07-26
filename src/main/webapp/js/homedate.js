//头部房子信息
let h = new Vue({
    el: "#getTitle",
    data() {
        return {
            title: {
                id: 0,
                title: "桂林理工大学",
                basemoney: 2500,
                type: 3,
                area: 140,
                toward: "朝南",
                address: "北京"
            }
        };
    },
    mounted() {
        this.getHouse();
    },
    methods: {
        getHouse() {
            const searchParams = new URLSearchParams(window.location.search);
            const id = searchParams.get('id');
            axios({
                url: "/house/getHouseById?id=" + id,
                method: "get",
            }).then(resp => {
                if (resp.data.code == 1){
                    this.title = resp.data.data;
                }else{
                    this.$message.error(resp.data.msg)
                }

            })
        }
    }
})

//根据房子id获取房主信息
new Vue({
    el: "#getUser",
    data() {
        return {
            user: {
                id: 0,
                realname: "翁修城",
                phone: "18577933140",
                tx: "image/img/laobiao3.jpg"
            }
        }
    },
    mounted() {
        this.getuser();
    },
    methods: {
        getuser() {
            const searchParams = new URLSearchParams(window.location.search);
            const id = searchParams.get('id');
            axios({
                url: "/user/getUserByHouseId?id=" + id,
                method: "get",
            }).then(resp => {
                if (resp.data.code == 1){
                    this.user = resp.data.data;
                    this.user.tx = `/common/download?name=${this.user.tx}`
                }else{
                    this.$message.error(resp.data.msg)
                }

            })
        }
    }
})


new Vue({
    el: '#home',
    data() {
        return {
            house: {
                basemoney: 511,
                address: "北京",
                area: "200",
                toward: "朝东",
                fwzx: 1,
                floor: 52,
                type: 3
            }
        };
    },
    created() {
        // 遍历房屋配套设施数据，创建对象并添加到houseInfo数组中
        this.getHouse();
    },
    methods: {
        getHouse() {
            const searchParams = new URLSearchParams(window.location.search);
            const id = searchParams.get('id');
            axios({
                url: "/house/getHouseById?id=" + id,
                method: "get",
            }).then(resp => {
                this.house = resp.data.data;
            })
        }
    },
})

new Vue({
    el: "#getHouseAdd",
    data() {
        return {
            houseInfo: [], // 存储房屋配套设施的数组
            houseData: [{icon: '', name: '冰箱',}, {icon: '', name: '洗衣机',}, {icon: '', name: '热水器',},
                {icon: '', name: '宽带',}, {icon: '', name: '沙发',}, {icon: '   ', name: '可做饭',},
                {icon: '', name: '电视',}, {icon: '', name: '空调',}, {icon: '  ', name: '衣柜',},
                {icon: '', name: '床',}, {icon: '', name: '卫生间',},
                {icon: '', name: '阳台',}, {icon: '', name: '暖气',},
            ],
            houseAdd: [],
        };
    },
    created() {
        // 遍历房屋配套设施数据，创建对象并添加到houseInfo数组中
        this.getHouseAdd();
    },
    methods: {
        getHouseAdd() {
            const searchParams = new URLSearchParams(window.location.search);
            const id = searchParams.get('id');
            axios({
                url: "house/getHouseAdd?id=" + id,
                method: "get",
            }).then(resp => {
                if(resp.data.code == 1){
                    this.houseAdd = resp.data.data;
                    //遍历集合
                    this.houseData.forEach(item => {
                        if (this.houseAdd.includes(item.name)) { // 判断后端返回的集合中是否存在当前配套设施
                            const facility = {
                                icon: item.icon,
                                name: item.name,
                                // 在此处添加后端返回的属性值
                            };
                            this.houseInfo.push(facility);
                        }
                    });
                }else {
                    this.$message.error(resp.data.msg)
                }
            })
        }
    }
})

/*
// 创建地理编码对象
var geocoder;
AMap.plugin("AMap.Geocoder", function () {
    geocoder = new AMap.Geocoder();
})

// 地址解析
geocoder.getLocation(h.title.address, function (status, result) {
    if (status === 'complete' && result.info === 'OK') {
        // 解析成功，获取坐标信息
        console.log(result)
        var location = result.geocodes[0].location;
        console.log('经度：' + location.lng);
        console.log('纬度：' + location.lat);
        // 在地图上显示坐标点
        var map = new AMap.Map('map', {
            center: [location.lng, location.lat],
            zoom: 15
        });
        var infoWindow = new AMap.InfoWindow({ //创建信息窗体
            isCustom: true,  //使用自定义窗体
            content: '<div>信息窗体</div>', //信息窗体的内容可以是任意html片段
            offset: new AMap.Pixel(16, -45)
        });
        var onMarkerClick = function (e) {
            infoWindow.open(map, e.target.getPosition());//打开信息窗体
            //e.target就是被点击的Marker
        }
        var marker = new AMap.Marker({
            position: [location.lng, location.lat]
        });
        map.add(marker);
        marker.on('click', onMarkerClick);//绑定click事件
    } else {
        // 解析失败
        console.log('地址解析失败');
    }
});*/

// 延迟加载地图
function delayedMapLoad() {
    // 创建地理编码对象
    var geocoder;
    AMap.plugin("AMap.Geocoder", function () {
        geocoder = new AMap.Geocoder();
    })

    // 在延迟加载函数中执行地图相关代码
    geocoder.getLocation(h.title.address, function (status, result) {
        if (status === 'complete' && result.info === 'OK') {
            // 解析成功，获取坐标信息
            console.log(result)
            var location = result.geocodes[0].location;
            console.log('经度：' + location.lng);
            console.log('纬度：' + location.lat);
            // 在地图上显示坐标点
            var map = new AMap.Map('map', {
                center: [location.lng, location.lat],
                zoom: 15
            });
            var infoWindow = new AMap.InfoWindow({ //创建信息窗体
                isCustom: true,  //使用自定义窗体
                content: '<div>信息窗体</div>', //信息窗体的内容可以是任意html片段
                offset: new AMap.Pixel(16, -45)
            });
            var onMarkerClick = function (e) {
                infoWindow.open(map, e.target.getPosition());//打开信息窗体
                //e.target就是被点击的Marker
            }
            var marker = new AMap.Marker({
                position: [location.lng, location.lat]
            });
            map.add(marker);
            marker.on('click', onMarkerClick);//绑定click事件
        } else {
            // 解析失败
            console.log('地址解析失败');
        }
    });
}

// 延迟1秒后执行地图加载
setTimeout(delayedMapLoad, 500);