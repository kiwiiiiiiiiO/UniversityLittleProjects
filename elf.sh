
#Snake Game by Jennifer Ou
##### variable area
start=(
	'-----------------------------------------------------------------'
	'|                ~~~~~   E L F   G A M E  ~~~~~                 |'
	'|                                                               |'
    '|         ~    get maximum scores in 100 steps!                 |'
    '|              +   food_even  ->   score increase!              |'
    '|              -   food_odd -> poison ((;-;))                   |'
    '|                                                               |'
    '|         ~    use i(u), j(l), k(d) ,l(r) to control direction  |'
    '|         ~    Watch Out!! Boundaries are deadly!!!             |'
    '|                                                               |'
	'|         *    Press <Enter> to start the game                  |'
	'|         *    Press <q> to quit the game                       |'
	'|                                                               |'
	'|                                                               |'
	'|           ~~               enjoy!               ~~            |'
	'-----------------------------------------------------------------'
);
finish=(
    '----------------------------------------------------------------'
    '|                ~~~~~ G A M E     O V E R ~~~~~               |'
    '|                                                              |'
    "                          ~ Score :                            "
    '|                                                              |'
    '|             *    Press <n> to start the new game             |'
    '|             *    Press <q> to exit the game                  |'
    '|                                                              |'
    '|                                                              |'
    '|           ~~             good job!              ~~           |'
    '|              AUTHOR : 410977008 Jennifer                     |'
    '----------------------------------------------------------------'
);
# boundary 長寬
boundary_x=$(($(tput lines)-5));
boundary_y=$(($(tput cols)-5));
# 畫筆
bou_pen="\033[35;42m*\033[0m";
elf_pen="\033[43;31mE\033[0m";
eraser=" ";
# 食物畫筆
food0_pen="\033[41;45m0\033[0m";
food1_pen="\033[41;45m1\033[0m";
food2_pen="\033[41;45m2\033[0m";
food3_pen="\033[41;45m3\033[0m";
food4_pen="\033[41;45m4\033[0m";
food5_pen="\033[41;45m5\033[0m";
food6_pen="\033[41;45m6\033[0m";
food7_pen="\033[41;45m7\033[0m";
food8_pen="\033[41;45m8\033[0m";
food9_pen="\033[41;45m9\033[0m";

##### logic area
# 遊戲開始
game_start(){
    clear;
    sc=0;
    # print ~Game Start~
    for each in "${start[@]}";
    do
        echo "$each";
    done
}

# 遊戲結束
game_finish(){
    clear;
    # print ~Game Over~
    for each in "${finish[@]}";
    do
        echo "$each";
    done
    echo "\033[4;38H\033[43m${sc}\033[0m";
}

# 初始化遊戲環境
game_init(){
    trap 'game_exit;' SIGTERM SIGINT; # 用trap抓取signit訊號
    stty -echo;         # turns off input echoing
    tput civis;         # clear the cursor
    tput smcup;         # estores the screen and repositions the cursor
    clear;          # clear the screen
}
 
## 玩家離開遊戲
game_exit(){
    clear;
    stty echo;
    tput rmcup;
    tput cvvis;
    exit 0;
}


# 初始化boundary(著色、標記)
boundary_init(){
    clear;
    for(( i = 1; i <= ${boundary_x}; i++));
    do
        echo "\033[$i;0H${bou_pen}";
        echo "\033[$i;${boundary_y}H${bou_pen}";
    done
    for(( i= 1; i <= ${boundary_y}; i++));
    do
        echo "\033[0;${i}H${bou_pen}";
        echo "\033[${boundary_x};${i}H${bou_pen}";
    done
}

## 初始化小精靈
elf_init(){
    # 精靈初始位置
    elfX=`expr $boundary_x / 2`;
    elfY=`expr $boundary_y / 2`;
    # 初始方向
    dir="r";
    # 畫出elf
    echo "\033[${elfX};${elfY}H${elf_pen}";
}
# elf移動
elf_move(){
    pelfX=$elfX;
    pelfY=$elfY;
    case $dir in
        d)  elfX=$(($elfX+1));;
        u)  elfX=$(($elfX-1));;
        r)  elfY=$(($elfY+1));;
        l)  elfY=$(($elfY-1));;
    esac
    echo "\033[${pelfX};${pelfY}H${eraser}";
    # 如果有碰到食物
    (($elfX==$foo0X)) && (($elfY==$foo0Y)) && sc=`expr $sc + 10`;
    (($elfX==$foo1X)) && (($elfY==$foo1Y)) && sc=`expr $sc - 1`;
    (($elfX==$foo2X)) && (($elfY==$foo2Y)) && sc=`expr $sc + 2`;
    (($elfX==$foo3X)) && (($elfY==$foo1Y)) && sc=`expr $sc - 3`;
    (($elfX==$foo4X)) && (($elfY==$foo4Y)) && sc=`expr $sc + 4`;
    (($elfX==$foo5X)) && (($elfY==$foo5Y)) && sc=`expr $sc - 5`;
    (($elfX==$foo6X)) && (($elfY==$foo6Y)) && sc=`expr $sc + 6`;
    (($elfX==$foo7X)) && (($elfY==$foo7Y)) && sc=`expr $sc - 7`;
    (($elfX==$foo8X)) && (($elfY==$foo8Y)) && sc=`expr $sc + 8`;
    (($elfX==$foo9X)) && (($elfY==$foo9Y)) && sc=`expr $sc - 9`;
    # 如果碰到邊界 -> break
    (( (($elfX>=$(($boundary_x)))) || (($elfX<=1)) || (($elfY>=$boundary_y)) || (($elfY<=1)) )) && break;
    echo "\033[${elfX};${elfY}H${elf_pen}";
}
# 初始化食物
food_init(){
    # 食物初始位置
    foo0X=$(( $RANDOM % (${boundary_x}-2)+2));  foo0Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo0X};${foo0Y}H${food0_pen}";
    foo1X=$(( $RANDOM % (${boundary_x}-2)+2));  foo1Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo1X};${foo1Y}H${food1_pen}";
    foo2X=$(( $RANDOM % (${boundary_x}-2)+2));  foo2Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo2X};${foo2Y}H${food2_pen}";
    foo3X=$(( $RANDOM % (${boundary_x}-2)+2));  foo3Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo3X};${foo3Y}H${food3_pen}";
    foo4X=$(( $RANDOM % (${boundary_x}-2)+2));  foo4Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo4X};${foo4Y}H${food4_pen}";
    foo5X=$(( $RANDOM % (${boundary_x}-2)+2));  foo5Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo5X};${foo5Y}H${food5_pen}";
    foo6X=$(( $RANDOM % (${boundary_x}-2)+2));  foo6Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo6X};${foo6Y}H${food6_pen}";
    foo7X=$(( $RANDOM % (${boundary_x}-2)+2));  foo7Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo7X};${foo7Y}H${food7_pen}";
    foo8X=$(( $RANDOM % (${boundary_x}-2)+2));  foo8Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo8X};${foo8Y}H${food8_pen}";
    foo9X=$(( $RANDOM % (${boundary_x}-2)+2));  foo9Y=$(( $RANDOM % (${boundary_y}-2)+2));  echo "\033[${foo9X};${foo9Y}H${food9_pen}";
}

# 遊戲
game_new(){
    # 預設分數
    sc=0;
    # 初始化
    boundary_init;
    elf_init;
    food_init;
    # 預設步數
    steps=100;
    St=" Steps : ${steps} "
    echo "\033[${boundary_x}-5;1H${St}\c"
    while true;
    do
        read -n 1 anykey;
        case "$anykey" in
            q)  break;;
            k)  dir="d";;
            i)  dir="u";;
            l)  dir="r";;
            j)  dir="l";;
        esac
        St=" Steps : ${steps} ";
        steps=`expr $steps - 1`;
        echo "\033[${boundary_x}-5;1H${St}\c";
        [[ $steps -lt 0 ]]  && break;
        elf_move;
    done
}

main(){
    game_init;
    game_start;
    # 等待鍵盤輸入 -> start or exit
    while read -n 1 anykey;
    do
        [[ ${anykey:-enter} = enter ]] && break;
        [[ ${anykey:-enter} = q ]] && game_exit;
    done
    
    while true;
    do
        # 玩家開始遊戲
        game_new;
        # 玩家結束遊戲
        game_finish;
        # 等待鍵盤輸入 -> new or exit
        while read -n 1 anykey;
        do
            [[ $anykey = n ]] && break;
            [[ $anykey = q ]] && game_exit;
        done
    done
}
main;
