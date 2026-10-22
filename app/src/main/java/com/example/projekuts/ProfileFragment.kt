package com.example.projekuts

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.projekuts.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View =
        FragmentProfileBinding.inflate(i, c, false).root
}