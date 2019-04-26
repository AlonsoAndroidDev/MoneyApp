package com.example.moneyapp.ui.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.example.moneyapp.R
import com.example.moneyapp.ui.Categoria.CategoriaFragment
import com.example.moneyapp.ui.Configuracion.ConfiguracionFragment
import com.example.moneyapp.ui.Egreso.EgresoFragment
import com.example.moneyapp.ui.Estadisticas.EstadisticasFragment
import com.example.moneyapp.ui.Ingreso.IngresoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var myDrawer: DrawerLayout

    private lateinit var categoriaFragment: CategoriaFragment
    private lateinit var configuracionFragment: ConfiguracionFragment
    private lateinit var egresoFragment: EgresoFragment
    private lateinit var estadisticasFragment: EstadisticasFragment
    private lateinit var ingresoFragment: IngresoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initToolbar()
        initAppBar()
        initFragments()
        initNavigationView()
    }

    private fun initComponents() {
        myDrawer = myDrawerLayout
    }

    private fun initToolbar() {
        val toolbar = toolbar
        setSupportActionBar(toolbar)
    }

    private fun initAppBar() {
        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initFragments() {
        categoriaFragment = CategoriaFragment()
        configuracionFragment = ConfiguracionFragment()
        egresoFragment = EgresoFragment()
        estadisticasFragment = EstadisticasFragment()
        ingresoFragment = IngresoFragment()
    }

    private fun initNavigationView() {

        myNavigationView.menu.getItem(0).isChecked=true

        myNavigationView.setNavigationItemSelectedListener {

            myDrawerLayout.closeDrawers()

            selectedDrawerItem(it.itemId)

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                myDrawer.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun selectedDrawerItem(item: Int): Boolean{
        var selectedItem = 0

        when (item) {

            R.id.itemIngreso -> {
                loadFrag(categoriaFragment, R.id.frame_container)
                selectedItem = item
            }
            R.id.itemEgreso -> {
                loadFrag(egresoFragment, R.id.frame_container)
                selectedItem = item
            }
            R.id.itemEstadisticas -> {
                loadFrag(estadisticasFragment, R.id.frame_container)
                selectedItem = item
            }
            R.id.itemCategorias -> {
                loadFrag(categoriaFragment, R.id.frame_container)
                selectedItem = item
            }
            R.id.itemConfiguraciones -> {
                loadFrag(configuracionFragment, R.id.frame_container)
                selectedItem = item
            }
        }

        myNavigationView.menu.findItem(selectedItem).isChecked = true
        myDrawer.closeDrawers()
        return true
    }

    private fun loadFrag(fragment: Fragment, frameLayout: Int) {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(frameLayout, fragment)
            .commit()
    }
}
