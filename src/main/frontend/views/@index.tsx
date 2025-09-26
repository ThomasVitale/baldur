import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Icon } from '@vaadin/react-components';
import { ViewToolbar } from 'Frontend/components/ViewToolbar';

export const config: ViewConfig = {
  menu: {
    icon: 'vaadin:home',
    order: -100,
    title: 'Home',
  },
};

export default function MainView() {
    return (
        <main className="p-m flex flex-col box-border w-full h-full">
          <ViewToolbar title="Welcome!" />
          <div className="flex-grow flex flex-col items-center justify-center">
            <div className="flex flex-col items-center">
              <Icon src="icons/sun-o.svg" className="text-success" style={{ width: '200px', height: '200px' }} />
              <p>Here comes the sun!</p>
            </div>
          </div>
        </main>
    );
}
