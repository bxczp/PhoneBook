<template>
    <ul class="list">
      <li class="item"
          @click="handleLetterClick"
          @touchstart="handleTouchStart"
          @touchmove="handleTouchMove"
          @touchend="handleTouchEnd"
          v-for="(item,key) of letters"
          :key="item"
          :ref="item">{{item}}</li>
    </ul>
</template>

<script>
    export default {
        name: "Alphabet",
        data(){
          return{
            touchStatus:false,
            startY:0,
            timer:null
          }
        },
        props:{
          phoneBooks:Object
        },
        computed:{
          letters(){
            const lettes=[]
            for (let i in this.phoneBooks){
              console.log(i)
              lettes.push(i)
            }
            return lettes;
          }
        },
        methods:{
          handleLetterClick(e){
            console.log(e.target.innerText)
            this.$emit("change",e.target.innerText)
          },
          handleTouchStart(){
            this.touchStatus=true
            console.log("start")
          },
          handleTouchMove(e){
            console.log("move")
            if(this.timer){
              clearTimeout(this.timer)
            }
            this.timer=setTimeout(()=>{
              if(this.touchStatus){
                const touchY=e.touches[0].clientY-79
                const index=Math.floor((touchY-this.startY)/20)
                if(index>=0 && index<this.letters.length){
                  this.$emit("change",this.letters[index])
                }
              }
            },20)
          },
          handleTouchEnd(){
            this.touchStatus=false
            console.log("end")
          }
        },
        updated() {
          this.startY=this.$refs[this.letters[0]][0].offsetTop
        }
    }
</script>

<style lang="stylus" scoped>

  .list
    display: flex
    flex-direction: column
    justify-content: center
    position: absolute;
    top: 1.58rem;
    right: 0;
    bottom: 0;
    width: .4rem;
    .item
      line-height: .4rem
      text-align: center
</style>
