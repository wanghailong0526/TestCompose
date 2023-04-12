android 原生 layout 对应 compose 平替
FrameLayout <==> Box()
LinearLayout <==> Column()/Row()
RelativeLayout <==> Box()  使用 Modifier 调整组件位置

ConstraintLayout (Jetpack) <==>

ScrollView <==> 给需要滑动的组件添加 Modifier.verticalScroll Modifier.horizontalScroll
RecyclerView (Jetpack) <==> lazyColumn()/lazyRow()
ViewPager (Jetpack)  <==>  Pager()



layout_width/layoutHeight  
1.在 compose 中 不是强制的，默认什么都不写的时候为 wrap_content
2.match_parent 在 compose 中 为 ModifierMaxWidth/fillMaxHeight/fillMaxSize



Modifier
1.Modifier 不是 builder 设计模式
2.调用函数有顺序，顺序不同，效果不同
