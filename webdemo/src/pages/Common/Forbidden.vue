<template>
    <div class="forbidden-container">
      <h1>403 Forbidden</h1>
      <p>Sorry, you don't have permission to access this page.</p>
    </div>
</template>
  
<script>
export default {
    name: 'ForbiddenPage',
};
</script>

<style>
.forbidden-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    font-size: 2rem;
}
</style>
  