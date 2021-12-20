package com.example.android.room.gameOver

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.android.room.R
import com.example.android.room.databinding.FragmentGameOverBinding
import com.example.android.room.gameWon.*

private lateinit  var viewModel: GameWonViewModel
private lateinit  var viewModelFactory: GameWonViewModelFactory

class GameOverFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_over, container, false)

        viewModelFactory = GameWonViewModelFactory(GameOverFragmentArgs.fromBundle(requireArguments()).numAciertos, GameOverFragmentArgs.fromBundle(requireArguments()).numPreguntas, GameOverFragmentArgs.fromBundle(requireArguments()).puntuacion)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameWonViewModel::class.java)

        binding.tvPuntuacionOver.text = resources.getString(R.string.total_score,viewModel.score.value,
            viewModel.questionsCorrect.value, viewModel.numQuestions.value)

        binding.tryAgainButton.setOnClickListener {
            it.findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToGameFragment(viewModel.numQuestions.value!!))
        }

        binding.btnSalirOver.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent() : Intent {
        val args = GameOverFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.total_score,viewModel.score.value,
                viewModel.questionsCorrect.value, viewModel.numQuestions.value))
        return shareIntent
    }

    // Starting an Activity with our new Intent
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    // Showing the Share Menu Item Dynamically
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if(getShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share).isVisible = false
        }
    }

    // Sharing from the Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}