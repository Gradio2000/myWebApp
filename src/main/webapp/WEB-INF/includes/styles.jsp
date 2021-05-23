<style>

    th, td {
        width: 10px;
    }

    textarea {
        width: -webkit-fill-available;
    }


    html, body {
        height: 100%;
        margin: 0;
    }
    .content {
        min-height: 100%;
    }
    .content-inside {
        padding: 20px;
        padding-bottom: 50px;
    }
    .footer {
        height: 50px;
        margin-top: -50px;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
    }

    th, td {
        text-align: left;
        padding: 16px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }

    .btn {
        border: none;
        background-color: inherit;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }

    .mybtn {
        border: none;
        background-color: inherit;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }

    /* Green */
    .success {
        color: green;
    }

    .success:hover {
        background-color: #4CAF50;
        color: white;
    }

    /* Blue */
    .info {
        color: dodgerblue;
    }

    .info:hover {
        background: #2196F3;
        color: white;
    }

    .close{
        color: #81888b;
    }

    .close:hover {
        background: #99a1a5;
        color: white;
    }

    /* Orange */
    .warning {
        color: orange;
    }

    .warning:hover {
        background: #ff9800;
        color: white;
    }

    /* Red */
    .danger {
        color: red;
    }

    .danger:hover {
        background: #f44336;
        color: white;
    }

    /* Gray */
    .default {
        color: black;
    }

    .default:hover {
        background: #e7e7e7;
    }

    .cancelbtn {
        border: none;
        color: white;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        background-color: #f44336;
    }
    .cancelbtn:hover {background: #da190b;}

    .footer{
        position: relative;
        left:0px;
        bottom:0px;
        height:100px;
        width:100%;
    }
    html{
        height: 100%;
    }
    body{
        display: flex;
        flex-direction: column;
        height: 100%;
    }

    header{
        /* 0 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 0 0 auto;
    }
    .main-content{
        /* 1 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 1 0 auto;
    }
    footer{
        /* 0 flex-grow, 0 flex-shrink, auto flex-basis */
        flex: 0 0 auto;
    }

</style>