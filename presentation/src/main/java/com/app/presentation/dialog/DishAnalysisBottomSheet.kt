package com.app.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.presentation.databinding.ViewBottomSheetDialogBinding
import com.app.presentation.model.DishAnalysisDetails
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DishAnalysisBottomSheet(private val dishAnalysisDetails: DishAnalysisDetails) : BottomSheetDialogFragment() {

    private lateinit var mBinding: ViewBottomSheetDialogBinding

    companion object {
        const val TAG = "DishAnalysisBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = ViewBottomSheetDialogBinding.inflate(
            inflater,
            container,
            false
        )
        initializeContent()
        return mBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { dialog ->
            val sheetDialog = dialog as BottomSheetDialog
            val bottomSheetView = sheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheetView?.let { view ->
                val behavior = BottomSheetBehavior.from(view)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }


    private fun initializeContent() {
        dishAnalysisDetails.ingredientCount.takeIf { it != null }?.let {
            mBinding.tvItemCount.tvTextView.text = it.toString()
        } ?: {
            mBinding.tvItemCount.root.visibility = View.GONE
        }

        dishAnalysisDetails.topCharacters.getOrNull(0)?.let { pair ->
            mBinding.tvFirstTopCharacter.lblTextView.text = pair.first.toString().formatBottomSheetTitle()
            mBinding.tvFirstTopCharacter.tvTextView.text = pair.second.toString()
        } ?: {
            mBinding.tvFirstTopCharacter.root.visibility = View.GONE
        }

        dishAnalysisDetails.topCharacters.getOrNull(1)?.let { pair ->
            mBinding.tvSecondTopCharacter.lblTextView.text = pair.first.toString().formatBottomSheetTitle()
            mBinding.tvSecondTopCharacter.tvTextView.text = pair.second.toString()
        } ?: {
            mBinding.tvSecondTopCharacter.root.visibility = View.GONE
        }

        dishAnalysisDetails.topCharacters.getOrNull(2)?.let { pair ->
            mBinding.tvThirdTopCharacter.lblTextView.text = pair.first.toString().formatBottomSheetTitle()
            mBinding.tvThirdTopCharacter.tvTextView.text = pair.second.toString()
        } ?: {
            mBinding.tvThirdTopCharacter.root.visibility = View.GONE
        }
    }


    private fun String.formatBottomSheetTitle(): String {
        return "• ".plus(this).plus(" -> ")
    }
}