package com.example.myphase2.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myphase2.R
import com.example.myphase2.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }

//        with(binding.bottomNavigation){
//            this?.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
//                var fragment: Fragment? = null
//                when(item.itemId){
//                    R.id.nav_profile -> fragment = ProfileFragment()
//                    R.id.nav_news -> fragment = NewsFragment()
//                    R.id.nav_bookmark -> fragment = BookmarkFragment()
//
//                }
//
//            })
//        }

    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
            binding.emailTv.text = email
        }
        else{
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragment2ToLoginFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}