import { createGlobalStyle } from 'styled-components';


const GlobalStyle = createGlobalStyle`
    body, h1, h2, h3, p, ul, li, form, input, textarea, button{
        margin:0;
        padding:0;
        box-sizing : border-box;
        font-family: 'Noto Sans KR', sans-serif;

    }

    body{
        background-color: #f0f2f5;
        color: #1c1e21;
    }
    
    a{
        text-decoration: none;
        color: inherit;
    }
    button{
        cursor: pointer;
        border: none;
        background-color: #1877f2;
        color: white;
        padding: 10px 15px;
        border-radius: 6px;
        font-size: 1rem;
        font-weight: bold;

        &:hover {
            background-color: #166fe5;
        }
    }

    `

export default GlobalStyle;