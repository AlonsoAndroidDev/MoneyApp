package com.example.moneyapp.ui.Main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.example.moneyapp.R
import com.example.moneyapp.ui.Categoria.CategoriaFragment
import com.example.moneyapp.ui.Configuracion.ConfiguracionFragment
import com.example.moneyapp.ui.Egreso.EgresoFragment
import com.example.moneyapp.ui.Estadisticas.EstadisticasFragment
import com.example.moneyapp.ui.Ingreso.IngresoFragment
import com.example.moneyapp.ui.Inicio.InicioFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    private lateinit var inicioFragment: InicioFragment
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
        initActionBar()
        initFragments()
        initNavigationView()
    }

    private fun initComponents() {
        drawerLayout = myDrawerLayout
    }

    private fun initToolbar() {
        val myToolbar = toolbar
        setSupportActionBar(myToolbar)
    }

    private fun initActionBar() {
        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initFragments() {
        inicioFragment = InicioFragment()
        categoriaFragment = CategoriaFragment()
        configuracionFragment = ConfiguracionFragment()
        egresoFragment = EgresoFragment()
        estadisticasFragment = EstadisticasFragment()
        ingresoFragment = IngresoFragment()
    }

    private fun initNavigationView() {

        myNavigationView.menu.getItem(0).isChecked = true

        myNavigationView.setNavigationItemSelectedListener {
            val itemId = it.itemId
            selectedDrawerItem(itemId)
        }

        selectedDrawerItem(R.id.itemInicio)
    }

    private fun selectedDrawerItem(menuItemId: Int): Boolean {

        when (menuItemId) {

            R.id.itemInicio -> {
                loadFrag(inicioFragment, R.id.frame_container)
            }

            R.id.itemIngreso -> {
                loadFrag(ingresoFragment, R.id.frame_container)
            }
            R.id.itemEgreso -> {
                loadFrag(egresoFragment, R.id.frame_container)
            }
            R.id.itemEstadisticas -> {
                loadFrag(estadisticasFragment, R.id.frame_container)
            }
            R.id.itemCategorias -> {
                loadFrag(categoriaFragment, R.id.frame_container)
            }
            R.id.itemConfiguraciones -> {
                loadFrag(configuracionFragment, R.id.frame_container)
            }
        }

        myNavigationView.menu.findItem(menuItemId).isChecked = true
        drawerLayout.closeDrawers()
        return true
    }

    private fun loadFrag(fragment: Fragment, frameLayout: Int) {
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(frameLayout, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
