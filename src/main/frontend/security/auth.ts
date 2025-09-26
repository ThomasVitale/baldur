import { configureAuth } from '@vaadin/hilla-react-auth';
import { UserInfoService } from 'Frontend/generated/endpoints';

const auth = configureAuth(UserInfoService.getUserInfo, {
    getRoles: (user) => {
        return user.authorities
            .filter(s => s.startsWith("ROLE_"))
            .map(s => s.substring(5));
    }
})
export const useAuth = auth.useAuth 
export const AuthProvider = auth.AuthProvider
