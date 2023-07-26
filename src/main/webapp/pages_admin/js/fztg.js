new Vue({
    el: '#app',
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            totalCount: 0,
            pagination: {},
            dataList: [
                {
                    username: "zhangsan",
                    house_no: 1,
                    sort: 1
                }
            ],//当前页要展示的列表数据
            formData: {},//表单数据
            dialogFormVisible4Edit: false,//编辑表单是否可见
            house_no: "",
        }
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        this.getAll();
    },
    methods: {
        //列表
        getAll() {
            axios({
                method: "post",
                url: "/house/fztgPage",
                data: {
                    currentPage: this.currentPage,
                    pageSize: this.pageSize,
                    house_no: this.house_no,
                }
            }).then((res) => {
                if(res.data.code ==1){
                    this.dataList = res.data.data.records;
                    this.totalCount = res.data.data.total;
                    console.log(this.totalCount)
                }else{
                    this.$message.error(resp.data.msg)
                }

            })

        },
        //弹出编辑窗口
        handleUpdate(row) {
            //根据id查询数据
            axios({
                method: "get",
                url: "/house/fztgGetById?id=" + row.id,
            }).then((res) => {
                if(res.data.code ==1){
                    this.formData = res.data.data;
                    this.dialogFormVisible4Edit = true;
                }else{
                    this.$message.error(res.data.msg)
                }

            })

        },
        //编辑
        handleEdit() {
            //发送请求
            axios({
                method: "post",
                url: "/house/updateSort",
                data: {
                    id: this.formData.id,
                    sort: this.formData.sort
                }
            }).then((res) => {
                if(res.data.code ==1){
                    //弹窗
                    this.$message.success("修改成功")
                    //操作成功，关闭窗口
                    this.dialogFormVisible4Edit = false;
                }else{
                    this.$message.error(res.data.msg)
                }

            }).finally(() => {
                this.getAll();
            })
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