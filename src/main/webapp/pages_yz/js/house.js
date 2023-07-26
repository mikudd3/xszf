new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            list: ['游泳池', '健身房', '停车场', '花园', "洗衣机",
                "客厅沙发", "卫生间设施", "电视机", "暖气", "阳台",
                "厨房设施", "空调"], // 可选的配套列表
            // 对应的数据
            dataList: [{
                mph: 1,
                address: 1,
                basemoney: 1,
                yajin: 1,
                condition: 0,
                floor: 1,
                zfzz: 2,
                zt: 1,
            }],//当前页要展示的列表数据
            formData: {
                image: [],
                house_add: []
            },//表单数据
            dialogVisible: false, // 控制配套列表弹窗的显示/隐藏
            dialogFormVisible: false,//控制表单是否可见
            dialogFormVisible4Edit: false,//编辑表单是否可见,
            address: "",
            user_id: 0,
            dialogImageUrl: "../../image/0fbb52fa8ad746628b24c05585bbf40f.jpg",
            imageZmcl: [],
            imageUrl: [],
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getUser();
    },
    methods: {
        getUser() {
            axios({
                url: "/user/getUser",
                method: "get",
            }).then(resp => {
                if (resp.data.code == 1) {
                    this.user_id = resp.data.data.id;
                    console.log(this.id)
                    this.getAll();
                } else {
                    this.$message.error(resp.data.msg);
                }
            })
        },
        showList() {
            this.dialogVisible = true;
        },
        addItems() {
            this.dialogVisible = false;
        },
        handleUploadSuccess1(response, file) {
            this.formData.image.push(response.data) // 将图片地址保存到变量中
        },
        handleUploadSuccess2(response, file) {
            this.formData.zmcl = response.data // 将图片地址保存到变量中
        },
        handleRemove1(file, fileList) {
            // 找到要删除的文件在fileList中的索引
            const index = fileList.findIndex(item => item.uid === file.uid);
            if (index !== -1) {
                fileList.splice(index, 1); // 从fileList中移除要删除的文件
            }
            // 找到要删除的图片地址在变量中的索引
            const imgIndex = this.formData.image.findIndex(item => item === file.url);
            if (imgIndex !== -1) {
                this.formData.image.splice(imgIndex, 1); // 从变量中删除图片地址
            }
        },
        handleRemove2(file, fileList) {
            // 找到要删除的文件在fileList中的索引
            const index = fileList.findIndex(item => item.uid === file.uid);
            if (index !== -1) {
                fileList.splice(index, 1); // 从fileList中移除要删除的文件
            }
            // 找到要删除的图片地址在变量中的索引
            const imgIndex = this.formData.image.findIndex(item => item === file.url);
            if (imgIndex !== -1) {
                this.formData.zmcl.splice(imgIndex, 1); // 从变量中删除图片地址
            }
        },
        //列表
        getAll() {
            axios({
                method: "post",
                url: "/house/YzPage",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    address: this.address,
                    user_id: this.user_id,
                }
            }).then((res) => {
                if (res.data.code == 1) {
                    this.dataList = res.data.data.records;
                    this.totalCount = res.data.data.total;
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
        //弹出编辑窗口
        handleUpdate(row) {
            // this.dialogFormVisible4Edit = true;
            //根据id查询数据
            axios({
                method: "get",
                url: "/house/getHouseById?id=" + row.id,
            }).then((res) => {
                if (res.data.code == 1) {
                    this.formData = res.data.data;
                    //进行图片处理
                    for (let i = 0; i < this.formData.image.length; i++) {
                        this.imageUrl[i] = `/common/download?name=${this.formData.image[i]}`
                    }
                    for (let i = 0; i < this.formData.zmcl.length; i++) {
                        this.imageZmcl[i] = `/common/download?name=${this.formData.zmcl[i]}`
                    }
                    this.dialogFormVisible4Edit = true;
                } else {
                    this.$message.error(res.data.msg);
                }
            })

        },
        //编辑
        handleEdit() {
            //发送请求
            axios({
                method: "post",
                url: "/house/updateHouse",
                data: this.formData
            }).then((res) => {
                if (res.data.code == 1) {
                    //弹窗
                    this.$message.success("修改成功")
                    //操作成功，关闭窗口
                    this.dialogFormVisible4Edit = false;
                } else {
                    this.$message.error(res.data.msg);
                }
            }).finally(() => {
                this.getAll();
            })
        },

        // 删除
        handleDelete(row) {
            let status = row.zt === 1 ? 0 : 1;
            axios({
                method: "post",
                url: "/house/xjHouse",
                params: {
                    status: status,
                    id: row.id
                }
            }).then((res) => {
                if (res.data.code != 1){
                    this.$message.error(res.data.msg);
                }
            }).finally(() => {
                this.getAll();
            });
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.getAll();
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getAll();
        }
    }
})