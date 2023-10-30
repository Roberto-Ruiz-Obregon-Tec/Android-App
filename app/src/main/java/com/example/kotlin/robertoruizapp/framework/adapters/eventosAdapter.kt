package com.example.kotlin.robertoruizapp.framework.adapters

class eventosAdapter (val clickListener: EventosClickListener): RecyclerView.Adapter<eventosAdapter.ViewHolder>() {

    lateinit var data : List<Document>
    var results : Int = 0

    fun eventosAdapter (data: List<Document>) {
            this.data = data

    }

    fun eventosResults (results: Int?) {
        if (results != null) {
            this?.results = results
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int):ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_element_eventos, viewGroup, false)
        return ViewHolder(v, clickListener)
    }

}