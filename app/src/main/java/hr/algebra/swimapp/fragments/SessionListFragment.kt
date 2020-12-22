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

        val cards = generateTwoCards()
        rvSwimInfoList.adapter = SwimInfoAdapter(cards)
        rvSwimInfoList.layoutManager = LinearLayoutManager(requireContext())
        rvSwimInfoList.setHasFixedSize(true)
    }

    private fun initListeners() {
        ivNewSession.setOnClickListener {
            startActivity<NewSession>()
        }
    }

    private fun generateTwoCards(): List<SwimInfo> {
        val list = ArrayList<SwimInfo>()
        for (i in 1..3) {
            val date = getCurrentDateTime()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                list.add(
                    SwimInfo(
                        date.format("EEEE"),
                        80,
                        LocalTime.of(1, 12, 45),
                        2000
                    )
                )
            }
        }
        println(list.size)
        return list
    }
}