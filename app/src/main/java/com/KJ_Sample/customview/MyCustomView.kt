package com.KJ_Sample.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout

class MyCustomView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private lateinit var mStar1 : ImageView
    private lateinit var mStar2 : ImageView
    private lateinit var mStar3 : ImageView
    private var mSelected = 0

    init {
        initializeViews(context,attrs)
    }

    //レイアウトの初期化処理
    private fun initializeViews(context: Context,attrs: AttributeSet?){
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //レイアウトの展開
        inflater.inflate(R.layout.three_stars_indicator,this)
        if(attrs != null){
            //attrs.xmlに定義したスタイルを取得
            val typeA = context.obtainStyledAttributes(attrs,R.styleable.MyCustomView)
            mSelected = typeA.getInteger(0,0)
            //利用が終わったら、recycler()を呼ぶ
            typeA.recycle()
        }
    }

    //inflateが完了するタイミングでコールバックする
    override fun onFinishInflate() {
        super.onFinishInflate()
        mStar1 = findViewById(R.id.star1)
        mStar2 = findViewById(R.id.star2)
        mStar3 = findViewById(R.id.star3)
        //初回だけはXMLからの反映させるための第二引数のfarceをtrueとする
        setSelected(mSelected,true)
    }

    fun setSelected(select:Int){
        setSelected(select,false)
    }

    private fun setSelected(select:Int,force:Boolean){
        if(force || mSelected != select){
            if(2 > mSelected && mSelected < 0){
                return
            }
            mSelected = select
            when(mSelected){
                0 ->{
                    mStar1.setImageResource(R.drawable.star)
                    mStar2.setImageResource(R.drawable.empty_star)
                    mStar3.setImageResource(R.drawable.empty_star)
                }

                1->{
                    mStar1.setImageResource(R.drawable.empty_star)
                    mStar2.setImageResource(R.drawable.star)
                    mStar3.setImageResource(R.drawable.empty_star)
                }

                2->{
                    mStar1.setImageResource(R.drawable.empty_star)
                    mStar2.setImageResource(R.drawable.empty_star)
                    mStar3.setImageResource(R.drawable.star)
                }
            }
        }
    }

    fun getSelected(): Int {
        return mSelected
    }
}