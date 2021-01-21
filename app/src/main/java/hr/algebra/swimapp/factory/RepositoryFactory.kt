package hr.algebra.swimapp.factory

import android.content.Context
import hr.algebra.swimapp.dal.SqlHelper

fun getRepository(context: Context?) = SqlHelper(context)