<style>

    th, td {
        width: 10px;
    }

    textarea {
        width: -webkit-fill-available;
    }
    .mytd{
        text-align: center;
    }

    * {
        margin: 0;
        padding: 0;
    }
    .content {
        min-height: calc(100vh - 80px);
        margin-left: auto;
        margin-right: auto;
        width: 60%;
        padding: 10px;
        margin: auto;
    }

    .content-inside {
        padding: 20px;
        padding-bottom: 50px;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        /*border: 1px solid #ddd;*/
    }

    td {
        text-align: left;
        padding: 16px;
        width: revert;
    }
    th {
        text-align: center;
        padding: 16px;
        width: revert;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }

    .mytr{
        background-color: antiquewhite;
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
        background-color: #929998;
        /*padding: 14px 28px;*/
        font-size: 16px;
        cursor: pointer;
        display: inline-block;
    }
    .my-btn {
        padding-top: 20px;
        padding-left: 40px;
        padding-right: 40px;
        padding-bottom: 20px;
    }


    .false{
        color: #da190b;
    }

    .true{
        color: #2c9751;
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

    .infolbtn {
        border: none;
        color: white;
        padding: 14px 28px;
        font-size: 16px;
        cursor: pointer;
        background-color: dodgerblue;
    }
    .infolbtn:hover {background: #2e73b4;}

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

    .container-my-big {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        margin-right: auto;
        margin-left: auto;
        max-width: 1080px;
    }

    .container-my-md {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        /*margin-right: 0;*/
        margin-left: auto;
        max-width: 960px;
    }

    .container-my-sm {
        width: 100%;
        padding-right: var(--bs-gutter-x, 0.75rem);
        padding-left: var(--bs-gutter-x, 0.75rem);
        /*margin-right: 0;*/
    }

    .pagination a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        transition: background-color .3s;
    }

    .pagination a.active {
        background-color: dodgerblue;
        color: white;
    }

    .pagination a:hover:not(.active) {
        background-color: #ddd;
    }

    .countdown{
        color: #da190b;
        font-size: x-large;
        width: 175.75px;
        text-align: center;
    }

    .my-box {
        margin-top: 40px;
        -webkit-box-shadow: 7px 10px 11px -4px rgba(34, 60, 80, 0.18);
        -moz-box-shadow: 7px 10px 11px -4px rgba(34, 60, 80, 0.18);
        box-shadow: 7px 10px 11px -4px rgba(34, 60, 80, 0.18);
        background-color: #f8f6f6;
        padding-top: 20px;
        padding-left: 40px;
        padding-right: 40px;
        padding-bottom: 20px;
    }

    .my-box-hover:hover{
        background-color: #dcdcdc;
    }

    input[type=text], select, textarea, password {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

    .my-input {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

</style>