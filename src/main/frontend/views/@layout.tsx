import { Outlet, useLocation, useNavigate } from 'react-router';
import '@vaadin/icons';
import { AppLayout, Avatar, Button, Icon, MenuBar, MenuBarItemSelectedEvent, ProgressBar, Scroller, SideNav, SideNavItem } from '@vaadin/react-components';
import { Suspense } from 'react';
import { createMenuItems } from '@vaadin/hilla-file-router/runtime.js';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useAuth } from 'Frontend/security/auth';

export const config: ViewConfig = {
    loginRequired: true
}

function Header() {
  return (
    <div className="flex p-m gap-m items-center" slot="drawer">
      <Icon icon="vaadin:rocket" className="text-primary icon-l" />
      <span className="font-semibold text-l">Baldur</span>
    </div>
  );
}

function MainMenu() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <SideNav className="mx-m"
             onNavigate={({ path }) => path != null && navigate(path)}
             location={location}>
      {createMenuItems().map(({ to, icon, title }) => (
        <SideNavItem path={to} key={to}>
          {icon && <Icon icon={icon} slot="prefix" />}
          {title}
        </SideNavItem>
      ))}
    </SideNav>
  );
}

function UserMenu() {
  const { state, logout } = useAuth();
  const user = state.user;
  
  // Don't render if user is not loaded yet
  if (!user) {
    return null;
  }

  const formatDisplayName = (name: string) => {
    return name.replace(/([A-Z])/g, ' $1').trim();
  };

  const displayName = formatDisplayName(user.name);

  const items = [
    {
      component: (
        <>
          <Avatar theme="xsmall"
                  name={displayName}
                  colorIndex={5} className="mr-s" />
          {displayName}
        </>
      ),
      children: [
        { text: 'Logout', action: logout },
      ],
    },
  ];
  
  const onItemSelected = (event: MenuBarItemSelectedEvent) => {
    const action = (event.detail.value as any).action;
    if (action) {
      action();
    }
  };
  
  return (
    <MenuBar theme="tertiary-inline"
             items={items}
             onItemSelected={onItemSelected}
             className="m-m" slot="drawer" />
  );
}

export default function MainLayout() {
  return (
    <AppLayout primarySection="drawer">
      <Header />
      <Scroller slot="drawer">
        <MainMenu />
      </Scroller>
      <UserMenu />
      <Suspense fallback={<ProgressBar indeterminate={true} className="m-0" />}>
        <Outlet />
      </Suspense>
    </AppLayout>
  );
}
