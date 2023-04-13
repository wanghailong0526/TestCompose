android 原生 layout 对应 compose 平替
FrameLayout <==> Box()
LinearLayout <==> Column()/Row()
RelativeLayout <==> Box()  使用 Modifier 调整组件位置

ConstraintLayout (Jetpack) <==>

ScrollView <==> 给需要滑动的组件添加 Modifier.verticalScroll Modifier.horizontalScroll
RecyclerView (Jetpack) <==> lazyColumn()/lazyRow()
ViewPager (Jetpack)  <==>  Pager()

layout_width/layoutHeight

1. 在 compose 中 控件尺寸不是强制写的，什么都不写默认为 wrap_content
2. match_parent 在 compose 中 为 Modifier.fillMaxWidth()/fillMaxHeight()/fillMaxSize()
3. 设置具体值 Modifier.width(200.dp)/Modifier.Height(200.dp)/Modifier.size(200.dp)//size表示宽高都是都是这个尺寸

Modifier

1. 通用的参数使用 Modifier 设置;专项的设置使用函数参数
2. Modifier 不是 builder 设计模式,参数相同不会覆盖
3. 调用函数有顺序，顺序不同，效果不同
4. margin 是使用 padding 实现的 如下：

``` kotlin
Modifier
    .padding(10.dp)//margin 效果
    .padding(10.dp)//margin 效果
    .background(Color.Green)

Modifier
    .padding(10.dp)//margin 效果
    .background(Color.Black)
    .padding(10.dp),//padding 效果

Modifier
    .background(Color.Black)
    .padding(10.dp)//padding 效果
    .padding(10.dp),//padding 效果

```

compose 分包 从上到下的依赖关系 1依赖2 2依赖3 ...

1. material(3) material 风格 Button
2. foundation 基础控件 Text Image
3. animation 动画
4. ui 测量 布局 绘制
5. runtime 运行时状态管理 如：remember{} mutableState
6. compile 编译


三条原则

1. 所以写代码的时候依赖 material(3)就够了，也可以跳过，依赖 foundation 包
2. 如果需要 ui-tooling 需要单独依赖，用于预览
3. 如果需要 material-icon-extended(扩展矢量图标) 需要单独依赖


@composable

1. composable 函数首字母大写
2. composable 函数只能在其它 composable 函数中调用
3. composable 函数里只能写一个布局，使用一个 layout 包起来，多个的话会受到外部布局的影响，那样的话布局放置位置不确定
4. 
