import {create} from 'zustand';
import {persist} from 'zustand/middleware';


const useAuthStore = create(persist(
    (set) => ({
        token: null,
        isAuthenticated: false,
        setToken: (token)=> {
            set({token, isAuthenticated:!!token});
        },
        logout: () => {
            set({token: null, isAUuthenticated: false });
        },
    }),
    {
        name: 'auth-storage', // localStorage에 저장될 때 사용할 키   
    }
))

export default useAuthStore;