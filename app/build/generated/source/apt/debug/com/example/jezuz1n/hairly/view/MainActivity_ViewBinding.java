// Generated code from Butter Knife. Do not modify!
package com.example.jezuz1n.hairly.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.jezuz1n.hairly.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private IndexActivity target;

  @UiThread
  public MainActivity_ViewBinding(IndexActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(IndexActivity target, View source) {
    this.target = target;

    target.drawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawerLayout'", DrawerLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.appbar, "field 'toolbar'", Toolbar.class);
    target.navigationView = Utils.findRequiredViewAsType(source, R.id.navview, "field 'navigationView'", NavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IndexActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.drawerLayout = null;
    target.toolbar = null;
    target.navigationView = null;
  }
}
