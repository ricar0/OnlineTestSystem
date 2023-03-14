import Cookies from "js-cookie";
export const getToken = () => {
    return Cookies.get("TOKEN");
}

export const setToken = (token) => {
    Cookies.set("TOKEN", token, {expires: 30});
}

export const removeToken = () => {
    Cookies.remove("TOKEN");
}