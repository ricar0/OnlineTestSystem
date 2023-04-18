import Cookies from "js-cookie";
export const getToken = () => {
    return Cookies.get("TOKEN");
}
export const getRole = () => {
    return Cookies.get("ROLE");
}

export const setToken = (token) => {
    Cookies.set("TOKEN", token, {expires: 30});
}

export const setRole = (role) => {
    Cookies.set("ROLE", role, {expires: 30});
}

export const removeToken = () => {
    Cookies.remove("TOKEN");
    Cookies.remove("ROLE")
}