//package com.example.newstart.navigatonview
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.text.SpannableString
//import android.util.Log
//import android.view.*
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import androidx.navigation.NavController
//import androidx.navigation.Navigation
//import androidx.navigation.ui.NavigationUI
//import com.example.newstart.R
//import com.google.android.material.bottomnavigation.BottomNavigationItemView
//import com.google.android.material.bottomnavigation.BottomNavigationMenuView
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import java.lang.reflect.Field
//
//
//class DashboardActivity :AppCompatActivity() {
//    val MENU_HOME=100;
//    val MENU_NETWORK=101;
//    val MENU_EXPERIENCE=102;
//    val MENU_CHAT=103;
//    val MENU_MENU=104;
//    var  a=ArrayList<String>()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        var bol=true
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard)
//        val navController =
//            Navigation.findNavController(this, R.id.my_nav_host_fragment)
//        val bottomNavigationView =
//            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)
//
//        val header_title=findViewById<TextView>(R.id.header_title)
//        val tvMesh=findViewById<TextView>(R.id.tvMesh)
//        val tvCM=findViewById<TextView>(R.id.tvCM)
//        val tvGW=findViewById<TextView>(R.id.tvGW)
////        bottomNavigationView.menu.removeGroup()
//      //  bottomNavigationView.menu.add(R.id. mesh,fragment_id,sort_order,title)
//
//
////        val textView =
////            bottomNavigationView.findViewById<View>(R.id.experience)
////                .findViewById<View>(R.id.largeLabel) as TextView
////        textView.textSize = 8f
////        textView.setTextColor(ContextCompat.getColor(this,R.color.teal_200))
//
//
//         val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
//
//              header_title.text=destination.label
//        }
//        navController.addOnDestinationChangedListener(listener)
//        val menu: Menu = bottomNavigationView.menu
//
//       // addMenu(menu)
//
//
//      //  ( ( bottomNavigationView[1] as ViewGroup).getChildAt(0) as TextView).text="sadf"
//       // removePaddingFromBottomNavigationItem(bottomNavigationView);
//
////        val menuView = bottomNavigationView.getChildAt(0) as? ViewGroup ?: return
////        menuView.forEach {
////            it.findViewById<View>(R.id.largeLabel)?.setPadding(0, 0, 0, 0)
////        }
//        editBottomNavigationViewItems(bottomNavigationView);
//      //  removePaddingFromBottomNavigationItem(bottomNavigationView);
//        //removePaddingFromNavigationItem(bottomNavigationView)
//
//        // menu.findItem(R.id.fragment4).isVisible = false
//
//
//        header_title.setOnClickListener {
//
//            if(bol){
////                menu.add( "Chat")
////                    .setIcon(R.drawable.ic_baseline_chat_bubble_24)
//                bol=false
//                menu.findItem(R.id.fragment4).isVisible = true
//
//            }else{
////                menu.removeItem(R.id.fragment4)
//                menu.findItem(R.id.fragment4).isVisible = false
//
//                bol=true
//            }
//
//        }
//
//        tvMesh.setOnClickListener {
//            bottomNavigationView.invalidate();
//
//            setMesh(menu)
//
//        }
//
//        tvCM.setOnClickListener {
//            bottomNavigationView.invalidate();
//
//            setCM(menu)
//
//        }
//        tvGW.setOnClickListener {
//            bottomNavigationView.invalidate();
//
//            setGW(menu)
//
//        }
//
//
//
//
//    }
//
//    private fun addMenu(menu: Menu) {
////        bottomNavigationView.menu.add(R.id. mesh,fragment_id,sort_order,title)
//        menu.add(0,R.id.fragment1 , 0, "Home").setIcon(R.drawable.ic_baseline_home_24)
//        menu.add(0, R.id.fragment2,0, "My\nExperience").setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_camera_24))
//        menu.add(0, R.id.fragment3, 0, "My\nNetwork").setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_wifi_24))
//        menu.add(0, R.id.fragment4, 0, "Chat").setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_chat_bubble_24))
//        menu.add(0, R.id.fragment5, 0, "Menu").setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_menu_24))
//
////        menu.findItem(MENU_EXPERIENCE).setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_camera_24))
////        menu.findItem(MENU_NETWORK).setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_wifi_24))
////        menu.findItem(MENU_CHAT).setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_chat_bubble_24))
////        menu.findItem(MENU_MENU).setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_menu_24))
//
//
//
////
//       // menu.get(1).setIcon(ContextCompat.getDrawable(this,R.drawable.ic_baseline_wifi_24))
//      //  menu.get(0) as TextView
//
//    }
//
//    private fun setMesh(menu: Menu) {
//        var positionOfMenuItem = 0 // or whatever...
//        val meshArray=ArrayList<BottomModel>()
//        meshArray.add(BottomModel("Home",R.drawable.ic_baseline_home_24))
//        meshArray.add(BottomModel("My Network",R.drawable.ic_baseline_wifi_24))
//        meshArray.add(BottomModel("My Experience",R.drawable.ic_baseline_camera_24))
//        meshArray.add(BottomModel("Chat",R.drawable.ic_baseline_chat_bubble_24))
//        meshArray.add(BottomModel("Menu",R.drawable.ic_baseline_menu_24))
////        menu.findItem(R.id.network).isVisible = true
////        menu.findItem(R.id.experience).isVisible = true
//
//        for ( mesh in meshArray){
//            val item: MenuItem = menu.getItem(positionOfMenuItem)
//            val s = SpannableString(mesh.name)
//        //    s.setSpan(ForegroundColorSpan(Color.RED), 0, s.length, 0)
//            item.setTitle(s)
//            item.setIcon(mesh.icon)
//            positionOfMenuItem++;
//        }
//
//    }
//    private fun setCM(menu: Menu) {
//        var positionOfMenuItem = 0 // or whatever...
//        val meshArray=ArrayList<BottomModel>()
//        meshArray.add(BottomModel("Home",R.drawable.ic_baseline_home_24))
//        meshArray.add(BottomModel("My Network",R.drawable.ic_baseline_wifi_24))
//        meshArray.add(BottomModel("My Experience",R.drawable.ic_baseline_camera_24))
//        meshArray.add(BottomModel("Chat",R.drawable.ic_baseline_chat_bubble_24))
//        meshArray.add(BottomModel("Menu",R.drawable.ic_baseline_menu_24))
//
////        menu.findItem(R.id.network).isVisible = false
////        menu.findItem(R.id.experience).isVisible = false
//        for ( mesh in meshArray){
//            val item: MenuItem = menu.getItem(positionOfMenuItem)
//            val s = SpannableString(mesh.name)
//          //  s.setSpan(ForegroundColorSpan(Color.RED), 0, s.length, 0)
//            item.setTitle(s)
//            item.setIcon(mesh.icon)
//
//            positionOfMenuItem++;
//        }
//
//    }
//
//    private fun setGW(menu: Menu) {
//        var positionOfMenuItem = 0 // or whatever...
//        val meshArray=ArrayList<BottomModel>()
//        meshArray.add(BottomModel("Home",R.drawable.ic_baseline_home_24))
//        meshArray.add(BottomModel("My GW",R.drawable.ic_baseline_wifi_24))
//        meshArray.add(BottomModel("Ex GW",R.drawable.ic_baseline_camera_24))
//        meshArray.add(BottomModel("Chat",R.drawable.ic_baseline_chat_bubble_24))
//        meshArray.add(BottomModel("Menu",R.drawable.ic_baseline_menu_24))
////        menu.findItem(R.id.network).isVisible = true
////        menu.findItem(R.id.experience).isVisible = true
//        for ( mesh in meshArray){
//            val item: MenuItem = menu.getItem(positionOfMenuItem)
//            val s = SpannableString(mesh.name)
//            //    s.setSpan(ForegroundColorSpan(Color.RED), 0, s.length, 0)
//            item.setTitle(s)
//            item.setIcon(mesh.icon)
//            positionOfMenuItem++;
//        }
//
//    }
//
//    data class BottomModel(val name:String,val icon:Int){
//
//    }
//
//    @SuppressLint("NewApi")
//    private fun editBottomNavigationViewItems(bottomNavigationView: BottomNavigationView) {
//        for (i in 0 until bottomNavigationView.childCount) {
//            try {
//                val item: View = bottomNavigationView.getChildAt(i)
//                if (item is BottomNavigationMenuView) {
//                    val menu = item as BottomNavigationMenuView
//                   // menu.minimumHeight=100
//                    //menu.measuredHeight=100
////                    menu.minimumHeight=100
//
//
//                   // item.h
//                    for (j in 0 until menu.childCount) {
//                        try {
//                            val menuItem: View = menu.getChildAt(j)
//                            val _icon: View =
//                                menuItem.findViewById(com.google.android.material.R.id.icon)
////
////                            val menuView =
////                                bottomNavigationView.getChildAt(0)
////                            for (i in 0 until menuView.childCount) {
//                               // val item = menuView.getChildAt(j) as BottomNavigationItemView
////                                val activeLabel =
////                                    menuItem.findViewById<View>(R.id.largeLabel)
////                                (activeLabel as? TextView)?.setPadding(0, 0, 0, 0)
////                            }
//
//                            val _image: ImageView =
//                                menuItem.findViewById(com.google.android.material.R.id.icon)
//                         //   _image.setPadding(0,0,0,0)
//
////                            val grp: ViewGroup =
////                            menuItem.findViewById(R.id.largeLabel)
////                            grp.setPadding(0,0,0,0)
//
//
//
//                            // not chosen item menu  GO
////                            val _small: View =
////                                menuItem.findViewById(R.id.smallLabel) //dependence com.google.android.material:material
//                            //View _small = menuItem.findViewById(android.support.design.R.id.smallLabel);// dependence android.support.design
//                            if (_small is TextView) {
//                            //    _small.setPadding(0, 0, 0, 0);// remove all padding
//                                val _tv = _small as TextView
//                             //   _tv.textSize = 14f // set size text
//                                _tv.ellipsize=null;
////                                _tv.minLines=2
//                                _tv.isSingleLine = false
//                                _tv.textAlignment=View.TEXT_ALIGNMENT_CENTER
//                                _tv.gravity=Gravity.BOTTOM
//
//                                if(j==1){
//                                    _tv.text="My\nNetwork"
//                                }
//                                if(j==2){
//                                    _tv.text="My\nExperience"
//                                }
//                             //   _tv.compoundDrawablePadding=10
////                                _tv.setPadding(0,0,0,0)
//                            } // not chosen item menu  END
//
//                            //this chosen item menu GO
//                            val _large: View =
//                                menuItem.findViewById(R.id.largeLabel) //dependence com.google.android.material:material
//                            //View _large = menuItem.findViewById(android.support.design.R.id.largeLabel);//dependence android.support.design.R.id.largeLabel
//                            if (_large is TextView) {
//                             //   _large.setPadding(0, 0, 0, 0) // remove all padding
//                                val _tv = _large as TextView
//                            //    _tv.textSize = 14f // set size text
//                                _tv.ellipsize=null;
////                                _tv.minLines=2
//
//                                _tv.isSingleLine = false
//                                _tv.gravity=Gravity.BOTTOM
//
//                                _tv.textAlignment=View.TEXT_ALIGNMENT_CENTER
//
//                                if(j==1){
//                                    _tv.text="My\nNetwork"
//                                }
//                                if(j==2){
//                                    _tv.text="My\nExperiences"
//                                }
//                               // _tv.compoundDrawablePadding=10
////                                _tv.setPadding(0,0,0,0)
//
//
//
//
//
//
////                                _tv.minLines=2
//
//                            } // this chosen item menu  END
//                        } catch (npei: NullPointerException) {
//                            Log.e("TAG", "get:BottomNavigationMenuView: " + npei.message)
//                        }
//                    }
//                }
//            } catch (npe: NullPointerException) {
//                Log.e("TAG", "get:BottomNavigationView: " + npe.message)
//            }
//        }
//    }
//
//    fun removePaddingFromBottomNavigationItem(bottom_navigation_menu: BottomNavigationView) {
//        val menuView =
//            bottom_navigation_menu.getChildAt(0) as BottomNavigationMenuView
//        for (i in 0 until menuView.childCount) {
//            val item = menuView.getChildAt(i) as BottomNavigationItemView
//            val activeLabel =
//                item.findViewById<View>(R.id.largeLabel)
//            (activeLabel as? TextView)?.setPadding(0, 0, 0, 0)
//        }
//    }
////
////    fun setItemHeight(height: Int): BottomNavigationView? {
////        // 1. get mMenuView
////        val mMenuView: BottomNavigationMenuView = getBottomNavigationMenuView()
////        // 2. set private final int mItemHeight in mMenuView
////        setField(mMenuView.javaClass, mMenuView, "itemHeight", height)
////        mMenuView.updateMenuView()
////        return this
////    }
//    private fun setField(
//        targetClass: Class<*>,
//        instance: Any,
//        fieldName: String,
//        value: Any
//    ) {
//        try {
//            val field: Field = targetClass.getDeclaredField(fieldName)
//            field.setAccessible(true)
//            field.set(instance, value)
//        } catch (e: NoSuchFieldException) {
//            e.printStackTrace()
//        } catch (e: IllegalAccessException) {
//            e.printStackTrace()
//        }
//    }
//
//
//    /**
//     * set margin top for icon
//     *
//     * @param position
//     * @param marginTop in px
//     */
////    fun setIconMarginTop(position: Int, marginTop: Int): BottomNavigationViewEx? {
////        /*
////        1. BottomNavigationItemView
////        2. private final int mDefaultMargin;
////         */
////        val itemView: BottomNavigationItemView = getBottomNavigationItemView(position)
////        setField(BottomNavigationItemView::class.java, itemView, "defaultMargin", marginTop)
////        mMenuView.updateMenuView()
////        return this
////    }
//
////    open fun removePaddingFromNavigationItem(bottomNavigationView: BottomNavigationView) {
////        val menuView =
////            bottomNavigationView.getChildAt(0)
////        for (i in 0 until menuView.childCount) {
////            val item = menuView.getChildAt(i) as BottomNavigationItemView
////            val activeLabel =
////                item.findViewById<View>(R.id.largeLabel)
////            (activeLabel as? TextView)?.setPadding(0, 0, 0, 0)
////        }
////    }
//
//}