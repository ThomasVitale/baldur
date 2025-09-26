import { LoginForm } from "@vaadin/react-components";
import { ViewConfig } from "@vaadin/hilla-file-router/types.js";
import { useSearchParams } from "react-router";

export const config: ViewConfig = {
    skipLayouts: true, 
    menu: {
        exclude: true 
    }
}

export default function LoginView() {
    const [searchParams] = useSearchParams()
    const hasError = searchParams.has("error"); 

    return (
        <main className="flex justify-center items-center w-full h-full">
            <LoginForm error={hasError} action="login"/> 
        </main>
    )
}