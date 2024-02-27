package com.breezevillepark.Kitchen.and.Bar.System.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class MainLayout  extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("BreezeVillePark");
        logo.addClassNames("text-l", "m-m");

        //Menu bar
        MenuBar menu = new MenuBar();
        MenuItem settings = createIconItem(menu, VaadinIcon.COG, "Settings");
        SubMenu EmployeesSubMenu = settings.getSubMenu();
        EmployeesSubMenu.addItem("Employees");

        //Logout Button
        Button logOut = new Button("Log Out");

        //Avatar
        Avatar companyLogo = new Avatar("breeze");
        companyLogo.setImage("/breeze.png");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, menu, companyLogo, logOut);
        header.setWidthFull();
        header.expand(logo);
        header.addClassNames("py-0", "px-m");
        header.getThemeList().set("dark", true);
        header.setAlignItems(FlexComponent.Alignment.CENTER);

        addToNavbar(header);

    }

    private MenuItem createIconItem(MenuBar menu, VaadinIcon iconName,
                                    String ariaLabel) {
        Icon icon = new Icon(iconName);
        MenuItem item = menu.addItem(icon);
        item.setAriaLabel(ariaLabel);

        return item;
    }

    private void createDrawer() {
       addToDrawer(new VerticalLayout(createSideNavigation()));
    }

    private Component createSideNavigation(){
        SideNav navigation = new SideNav();

        //Home
        SideNavItem home = new SideNavItem("Home");
        home.setPrefixComponent(VaadinIcon.HOME.create());

        //Employees link
        SideNavItem employeesLink = new SideNavItem("Employees");
        employeesLink.setPrefixComponent(VaadinIcon.GROUP.create());
        employeesLink.addItem(new SideNavItem("Employees List", EmployeeListView.class));
        employeesLink.addItem(new SideNavItem("Add Employee", EmployeeForm.class));

        //Inventory
        SideNavItem inventoryLink = new SideNavItem("Inventory");
        inventoryLink.setPrefixComponent(VaadinIcon.STORAGE.create());
        inventoryLink.addItem(new SideNavItem("Inventory List", InventoryListView.class));



        navigation.addItem(home,employeesLink, inventoryLink);
        return navigation;
    }
}
