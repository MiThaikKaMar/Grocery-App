package com.padc.grocerymtkm.dialogs

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.padc.grocerymtkm.R
import com.padc.grocerymtkm.data.vos.GroceryVO
import com.padc.grocerymtkm.mvp.presenters.MainPresenter
import com.padc.grocerymtkm.mvp.presenters.impls.MainPresenterImpl
import kotlinx.android.synthetic.main.dialog_add_grocery.*
import kotlinx.android.synthetic.main.dialog_add_grocery.view.*
import java.io.IOException

class GroceryDialogFragment : androidx.fragment.app.DialogFragment() {

    companion object {
        const val TAG_ADD_GROCERY_DIALOG = "TAG_ADD_GROCERY_DIALOG"
        const val BUNDLE_NAME = "BUNDLE_NAME"
        const val BUNDLE_DESCRIPTION = "BUNDLE_DESCRIPTION"
        const val BUNDLE_AMOUNT = "BUNDLE_AMOUNT"
        const val BUNDLE_IMAGE = "BUNDLE_IMAGE"

        const val PICK_IMAGE = 100
        private var mbitMap : Bitmap? = null

        fun newFragment(): GroceryDialogFragment {
            return GroceryDialogFragment()
        }
    }

    private lateinit var mPresenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_grocery, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        view.etGroceryName?.setText(arguments?.getString(BUNDLE_NAME))
        view.etDescription?.setText(arguments?.getString(BUNDLE_DESCRIPTION))
        view.etAmount?.setText(arguments?.getString(BUNDLE_AMOUNT))
        Glide.with(view.context)
            .load(arguments?.getString(BUNDLE_IMAGE))
            .into(iv_grocery)


        view.btnAddGrocery.setOnClickListener {
            val groceryVO = GroceryVO("")
//                groceryVO.name=etGroceryName.text.toString()
//                groceryVO.description=etDescription.text.toString()
//                groceryVO.amount=etAmount.text.toString().toInt()
//
//            mbitMap?.let { bitmap ->
//                mPresenter.onTapAddGrocery(groceryVO, bitmap)
//            }
//            dismiss()


            if(mbitMap!=null){
                groceryVO.name = etGroceryName.text.toString()
                groceryVO.description = etDescription.text.toString()
                groceryVO.amount = etAmount.text.toString().toInt()

                mbitMap?.let { bitmap ->
                    mPresenter.onTapAddGrocery(groceryVO, bitmap)
                }
                dismiss()

            }else{
                mPresenter.onTapAddGroceryWithoutImage(
                    etGroceryName.text.toString(),
                    etDescription.text.toString(),
                    etAmount.text.toString().toInt(),
                    arguments?.getString(BUNDLE_IMAGE).toString()
                )

                dismiss()
            }

        }

        btn_select.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE)
        }
    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = ViewModelProviders.of(it).get(MainPresenterImpl::class.java)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data
            try {

                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source? =
                            activity?.contentResolver?.let { it1 -> ImageDecoder.createSource(it1, it) }
                        val bitmap = source?.let { it1 -> ImageDecoder.decodeBitmap(it1) }
                        mbitMap = bitmap
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, it
                        )
                        mbitMap =  bitmap
                        val imageString = activity?.contentResolver?.openInputStream(filePath)
                        val photo = BitmapFactory.decodeStream(imageString)

                        Glide.with(this)
                            .load(filePath)
                            .into(iv_grocery)
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}