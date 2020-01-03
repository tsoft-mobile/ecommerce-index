package com.tsoftmobile.library.index.model.data

data class TypeItem(val type: String?, val type_id: String?){
    override fun toString(): String {
        return "($type:$type_id)"
    }
}