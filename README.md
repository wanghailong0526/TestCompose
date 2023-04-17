# android 原生 layout 对应 compose 平替

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

# Modifier

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

# compose 分包 从上到下的依赖关系 1依赖2 2依赖3 ...

1. material(3) material 风格 Button
2. foundation 基础控件 Text Image
3. animation 动画
4. ui 测量 布局 绘制
5. runtime 运行时状态管理 如：remember{} mutableState
6. compile 编译

# 三条原则

1. 所以写代码的时候依赖 material(3)就够了，也可以跳过，依赖 foundation 包
2. 如果需要 ui-tooling 需要单独依赖，用于预览
3. 如果需要 material-icon-extended(扩展矢量图标) 需要单独依赖

# @composable

1. composable 函数首字母大写
2. composable 函数只能在其它 composable 函数中调用
3. composable 函数里只能写一个布局，使用一个 layout 包起来，多个的话会受到外部布局的影响，那样的话布局放置位置不确定

# 页面刷新

1. 包括: 组合 布局 绘制
2. StateRecord: 变量
3. Snapshot: 整个状态，可以对应多个 StateRecord, 一个 StateRecord 对应一个 Snapshot
4. 系统有多个 Snapshot 的时候，他们是有先后关系的
5. 同一个 StateObject 的每个 StateRecord,都有它们对应的 Snapshot 的 id
6. StateRecord 和 Snapshot 就算不直接对应，只要 StateRecord 的 Snapshot 对另一个是有效的，另一个就能取到
   StateRecord
7. 两个订阅过程
    1. 对 Snapshot 中读写 StateObject 对象的订阅，分别订阅读和写，所以有两个接收者 readObserver 和
       writeObjerver
       订阅发生在 Snapshot 创建的时候，通知发生在读和写的时候
    2. 对每个 StateObject 的应用做订阅
       订阅发生在 第一个 readObserver 被调用的时候，通知发生在 StateObject 新值被应用的时候
8. get():记录读
9. set():标记失效
10. 应用事件:标记失效
11. 9 和 10 会引起界面刷新
12. mutableStateOf() 使用 = 与 by 委托关键字,如果使用 = 要使用变量的 name.value,如果使用 by 要使用
    变量的 name

    ``` kotlin
    //使用 =
    val name = mutableStateOf("whl")
    setContent{
        Text(name.value)
        name.value = "王海龙" //更新 name.value 的值，下次界面刷新的时候会更新 Text 的内容
    }
    
    //使用 by 委托关键字
    var name by mutableStateof("whl")
    setContent{
        Text(name)
        name = "王海龙" //更新 name 的值，下次界面刷新的时候会更新 Text 的值
    }
    ```

# mutableStateOf

1. 使用
    1. var name by mutableStateOf("")
       2.作用
    1. 监听变量的变化，重组时刷新界面

# remember 函数起到缓存作用，多次调用只执行一次，如果值不改变就使用缓存的值

1. recompose 重组时相同作用域下，变量的反复初始化，如下：
    ``` kotlin
    setContent{
        var name by remember{mutableStateof("whl")}
        Text(name)
        name = "王海龙"
    }
    ```
2. 带参数 remember(Key...){Key.xxx} 可以有多个 Key，当 Key 对象的内部条件就是后面的{}里的发生变化时，界面发生变化
   ```kotlin
   //只有当 value 参数改变时才会更新 Text 的值
   @Composable
   fun ShowMsg(value : String) {
        val length = remember(value){value.length}
        Text("长度是${length}")
   }
   ```
3. var name by remember{mutableStateof("whl")}
    1. 使用 by 是因为 mutableStateof 返回 State 对象是个代理，直接使用代理的话，只能使用 by 关键字

compose 函数传参数就是 无状态的，因为状态在外部，不传参数，就是有状态的，状态在内部

1. StateLess: 无状态,指的是控件的属性，属性在使用过值后 compose 就将属性的值丢弃了。无法再次获取到控件属性的值
2. State Hoisting 状态提升: 把设置给控件属性值的变量向上提到外面，
   这样变量的值就是控件属性的值，我们就能获取到控件的属性值了，但是尽量不往上提

# 单向数据流

1. 数据从上向下传递，事件从下向上传递
2. 在 compose 中的应用
    1. TextField
    2. BasicTextField

# @Stable 注解 意思是稳定的 @Immutable(不变的)与 @Stable 效果相同

1. 手动标记接口或类可靠性的方式，如下：
    1. data class User(var name:String) var 可变所以被看作是不可靠的
    2. data class User(val name:String) val 不可变所以被看作是可靠的
    3. 在 1 中使用 @Stable 注解标记类是可靠的，这样在 界面重组时不同的 User 对象，相同的值，不会重复刷新页面

2. 使用条件
    1. 现在相等就永远相等 意思是不要使用 data class 因为它会自动实现 equals 方法，导致不同的对象属性不相等
    2. 当 public 属性改变时，要能通知到使用到该属性的界面元素上 意思是，公开属性全部要使用 by
       mutableStateOf
       包起来
    3. 公开属性需要全部是稳定的(可靠的)  意思是实战中几乎不用考虑这种情况，默认就符合了这条

3. 实战中我们如何写一个类，如下：
   ```kotlin
    class User(name:String){
        // //第一个 name 是类的 public 属性，第二个 name 是上面传递的参数
        var name by mutableStateOf(name)
    }
   ```

# deviderStateOf()

1. 某一个状态依赖另一个状态使用此函数
2. 与 remember(Key) 带参数函数区别
    1. remember(Key) 是重组时如果 Key 被重新赋值的监听(重新赋值时刷新页面)
    2. derivedStateOf() 函数监听状态内部变化
    3. 实战中
        1. 函数参数 使用带参数的 remember(key)
        2. 监听状态内部变化 使用 derivedStateOf()
        3. 既是函数参数又是有状态 使用带参数的 remember(key) + derivedStateOf() 方式如下
           ```kotlin
           /**
            * @param names 数据列表
            * @param onClick 外部外部传入的点击事件
            */
            @Composable
            fun TestDerivedStateOf4(names: List<String>, onClick: () -> Unit) {
                val newNames by remember(names) { derivedStateOf { names.map { it.uppercase() } } }
                Text(newNames.toString(), modifier = Modifier.clickable { onClick.invoke() })
            }
           ```

# compositionLocal / staticCompositionLocal

1. 内部会记录变量的使用，然后变量改变时会重组页面
2. 实战使用场景
    1. 上下文
    2. 主题
    3. 如果一个变量几乎不会改变的时候
3. staticCompositionLocal 内部不会记录变量的使用，导致发生重组时界面会全量更新
4. 注意提供默认值，如果函数内需要的参数没有被 compositionLocal / staticCompositionLocal
   包起来,会出现没有值可以使用的情况

# 动画

1. AnimateXxxAsStateOf 状态转移型动画
    1. 代码如下：

```kotlin
@Composable
fun TestAnimateDpAsState() {
    var big by remember { mutableStateOf(false) }
    val size by animateDpAsState(targetValue = if (big) 96.dp else 48.dp)

    Box(
        Modifier
            .size(size)
            .background(Color.Green)
            .clickable { big = !big })
}
```
