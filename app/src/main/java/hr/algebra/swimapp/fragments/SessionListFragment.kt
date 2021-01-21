package hr.algebra.swimapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.swimapp.NewSession
import hr.algebra.swimapp.R
import hr.algebra.swimapp.components.SwimInfoAdapter
import hr.algebra.swimapp.dal.SWIM_INFO_PROVIDER_CONTENT_URI
import hr.algebra.swimapp.framework.startActivity
import hr.algebra.swimapp.model.SwimInfo
import hr.algebra.swimapp.utils.format
import hr.algebra.swimapp.utils.getCurrentDateTime
import kotlinx.android.synthetic.main.fragment_session_list.*
import java.time.LocalTime

class SessionListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_session_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

        val cards = collectSessionsFromDatabase()
        rvSwimInfoList.adapter = SwimInfoAdapter(cards)
        rvSwimInfoList.layoutManager = LinearLayoutManager(requireContext())
        rvSwimInfoList.setHasFixedSize(true)
    }

    private fun initListeners() {
        ivNewSession.setOnClickListener {
            startActivity<NewSession>()
        }
    }

    private fun collectSessionsFromDatabase(): MutableList<SwimInfo> {
        val list = ArrayList<SwimInfo>()
        val cursor = context?.contentResolver?.query(SWIM_INFO_PROVIDER_CONTENT_URI, null, null, null, null)
        if (cursor != null && cursor.moveToNext()) {
            println(cursor.getColumnIndex("_id"))
            list.add(
                SwimInfo(
                    cursor.getInt(cursor.getColumnIndex("_id")).toLong(),
                    cursor.getString(cursor.getColumnIndex("dayOfWeek")),
                    cursor.getInt(cursor.getColumnIndex("laps")),
                    cursor.getString(cursor.getColumnIndex("totalTime")),
                    cursor.getInt(cursor.getColumnIndex("distance"))
                )
            )
        }
        return list
    }
}