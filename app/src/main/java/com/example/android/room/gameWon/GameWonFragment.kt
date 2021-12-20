/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.room.gameWon

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.android.room.R
import com.example.android.room.databinding.FragmentGameWonBinding

private lateinit  var viewModel: GameWonViewModel
private lateinit  var viewModelFactory: GameWonViewModelFactory

class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false)

        viewModelFactory = GameWonViewModelFactory(GameWonFragmentArgs.fromBundle(requireArguments()).numAciertos, GameWonFragmentArgs.fromBundle(requireArguments()).numPreguntas, GameWonFragmentArgs.fromBundle(requireArguments()).puntuacion)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameWonViewModel::class.java)

        binding.tvPuntuacion.text = resources.getString(R.string.total_score,viewModel.score.value,
            viewModel.questionsCorrect.value, viewModel.numQuestions.value)

        binding.nextMatchButton.setOnClickListener {
            it.findNavController().navigate(
                GameWonFragmentDirections.actionGameWonFragmentToGameFragment(
                    viewModel.numQuestions.value!!
            )
            )
        }

        binding.btnSalirWon.setOnClickListener {
            //it.findNavController().popBackStack()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    // Creating our Share Intent
    private fun getShareIntent() : Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
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

